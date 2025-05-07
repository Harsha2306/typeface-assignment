package com.harsha.backend.validation;

import com.harsha.backend.entity.FileMetaData;
import com.harsha.backend.repository.FileMetaDataRepository;
import com.harsha.backend.utils.Constants;
import com.harsha.backend.utils.FileExtension;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FileValidator {

  private final FileMetaDataRepository fileMetaDataRepository;

  public void validateFile(String originalName, String fileExtension) {
    if (originalName.isBlank()) {
      throw new IllegalArgumentException("File name must not be blank");
    }

    if (!FileExtension.isValid(fileExtension))
      throw new IllegalArgumentException(Constants.UNSUPPORTED_FILE_TYPE + fileExtension);

    // prevent uploading file with same name
    Optional<FileMetaData> existing = fileMetaDataRepository.findByOriginalName(originalName);
    if (existing.isPresent())
      throw new IllegalArgumentException(Constants.FILE_ALREADY_EXISTS + originalName);
  }
}
