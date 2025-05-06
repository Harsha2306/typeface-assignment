package com.harsha.backend.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record FileMetaDataResponseDto(
    String id,
    String originalName,
    String fileType,
    long size,
    LocalDateTime uploadedAt) {}
