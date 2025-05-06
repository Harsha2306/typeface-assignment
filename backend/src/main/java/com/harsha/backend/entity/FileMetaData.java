package com.harsha.backend.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// TODO hanle error response if validation fails
public class FileMetaData {
  @Id private String id;

  @NotBlank(message = "Original file name must not be blank")
  private String originalName;

  @NotBlank(message = "Stored file name must not be blank")
  private String storedName;

  @NotBlank(message = "Path must not be blank")
  private String path;

  @NotBlank(message = "File type must not be blank")
  private String fileType;

  @Positive(message = "File size must be greater than zero")
  private long size;

  @NotNull(message = "Uploaded date must not be null")
  private LocalDateTime uploadedAt;
}
