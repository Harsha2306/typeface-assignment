package com.harsha.backend.exception;

import com.harsha.backend.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponseDto> handleIllegalArgument(IllegalArgumentException ex) {
    ErrorResponseDto errorResponseDto =
        ErrorResponseDto.builder()
            .timeStamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .ok(false)
            .error("Bad Request")
            .message(ex.getMessage())
            .build();
    return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDto> handleGenericException(Exception ex) {
    ErrorResponseDto errorResponseDto =
        ErrorResponseDto.builder()
            .timeStamp(LocalDateTime.now())
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .ok(false)
            .error("Internal Server Error")
            .message(ex.getMessage())
            .build();
    return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
