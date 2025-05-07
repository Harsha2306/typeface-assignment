package com.harsha.backend.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SuccessResponseDto<T>(
    LocalDateTime timeStamp, int status, boolean ok, String message, T data) {}
