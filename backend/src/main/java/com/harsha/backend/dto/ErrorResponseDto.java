package com.harsha.backend.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponseDto(
        LocalDateTime timeStamp, int status, boolean ok, String error, String message) {}
