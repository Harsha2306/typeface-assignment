package com.harsha.backend.service;

import com.harsha.backend.dto.FileMetaDataResponseDto;
import com.harsha.backend.entity.FileMetaData;
import com.harsha.backend.exception.FileStorageException;
import com.harsha.backend.repository.FileMetaDataRepository;
import com.harsha.backend.validation.FileValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
  private final FileMetaDataRepository fileMetaDataRepository;

  private final FileValidator fileValidator;

  @Value("${file.upload-dir}")
  private String uploadDir;

  public FileMetaDataResponseDto uploadFile(MultipartFile file) {
    // Ensure upload dir exists
    Path uploadPath = Paths.get(uploadDir);
    if (!Files.exists(uploadPath)) {
      try {
        Files.createDirectories(uploadPath);
      } catch (IOException e) {
        throw new FileStorageException("Could not create upload directory");
      }
    }

    // Generate unique file name
    String originalName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
    String fileExtension = getFileExtension(originalName);
    fileValidator.validateFile(originalName, fileExtension);

    String storedName = UUID.randomUUID() + "." + fileExtension;

    // Store file on disk
    Path filePath = uploadPath.resolve(storedName);
    try {
      Files.copy(file.getInputStream(), filePath);
    } catch (IOException e) {
      throw new FileStorageException("Failed to store file on disk: " + storedName);
    }

    // Build and save metadata
    FileMetaData fileMetaData =
        FileMetaData.builder()
            .originalName(originalName)
            .storedName(storedName)
            .path(filePath.toString())
            .fileType(file.getContentType())
            .size(file.getSize())
            .uploadedAt(LocalDateTime.now())
            .build();

    return mapToFileMetaDataResponseDto(fileMetaDataRepository.save(fileMetaData));
  }

  private FileMetaDataResponseDto mapToFileMetaDataResponseDto(FileMetaData fileMetaData) {
    return FileMetaDataResponseDto.builder()
        .id(fileMetaData.getId())
        .originalName(fileMetaData.getOriginalName())
        .fileType(fileMetaData.getFileType())
        .size(fileMetaData.getSize())
        .uploadedAt(fileMetaData.getUploadedAt())
        .build();
  }

  private String getFileExtension(String filename) {
    int dotIndex = filename.lastIndexOf('.');
    return (dotIndex == -1) ? "bin" : filename.substring(dotIndex + 1);
  }
}
