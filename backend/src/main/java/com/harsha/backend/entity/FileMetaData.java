package com.harsha.backend.entity;

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
public class FileMetaData {
  @Id private String id;
  private String originalName;
  private String storedName;
  private String path;
  private String fileType;
  private long size;
  private LocalDateTime uploadedAt;
}
