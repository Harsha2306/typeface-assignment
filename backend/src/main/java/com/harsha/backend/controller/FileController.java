package com.harsha.backend.controller;

import com.harsha.backend.dto.FileMetaDataResponseDto;
import com.harsha.backend.dto.SuccessResponseDto;
import com.harsha.backend.entity.FileMetaData;
import com.harsha.backend.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

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

  @GetMapping
  public ResponseEntity<SuccessResponseDto<List<FileMetaDataResponseDto>>> getFiles() {
    List<FileMetaDataResponseDto> fileMetaDataResponseDtoList = fileService.getFiles();

    SuccessResponseDto<List<FileMetaDataResponseDto>> successResponseDto =
        SuccessResponseDto.<List<FileMetaDataResponseDto>>builder()
            .timeStamp(LocalDateTime.now())
            .status(HttpStatus.OK.value())
            .ok(true)
            .message("Files fetched successfully")
            .data(fileMetaDataResponseDtoList)
            .build();

    return new ResponseEntity<>(successResponseDto, HttpStatus.OK);
  }

  @GetMapping("/{id}/content")
  public ResponseEntity<byte[]> getFileContent(@PathVariable String id) {
    byte[] content = fileService.getFileContent(id);
    FileMetaData meta = fileService.getFileMetaData(id);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.parseMediaType(meta.getFileType()));
    headers.setContentDisposition(
        ContentDisposition.inline().filename(meta.getOriginalName()).build());

    return new ResponseEntity<>(content, headers, HttpStatus.OK);
  }
}
