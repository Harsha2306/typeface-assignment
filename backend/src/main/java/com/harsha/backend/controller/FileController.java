package com.harsha.backend.controller;

import com.harsha.backend.dto.FileMetaDataResponseDto;
import com.harsha.backend.dto.SuccessResponseDto;
import com.harsha.backend.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/files")
@RequiredArgsConstructor
public class FileController {
  private final FileService fileService;

  @PostMapping("/upload")
  public ResponseEntity<SuccessResponseDto<FileMetaDataResponseDto>> uploadFile(
      @RequestParam("file") MultipartFile file) {
    FileMetaDataResponseDto fileMetaDataResponseDto = fileService.uploadFile(file);

    SuccessResponseDto<FileMetaDataResponseDto> successResponseDto =
        SuccessResponseDto.<FileMetaDataResponseDto>builder()
            .timeStamp(LocalDateTime.now())
            .status(HttpStatus.CREATED.value())
            .ok(true)
            .message("File uploaded successfully")
            .data(fileMetaDataResponseDto)
            .build();

    return new ResponseEntity<>(successResponseDto, HttpStatus.CREATED);
  }
}
