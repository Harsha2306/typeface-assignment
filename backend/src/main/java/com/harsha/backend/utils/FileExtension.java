package com.harsha.backend.utils;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum FileExtension {
  JPG("jpg"),
  PNG("png"),
  PDF("pdf");

  private final String extension;

  FileExtension(String extension) {
    this.extension = extension;
  }

  public static boolean isValid(String ext) {
    return Arrays.stream(values()).anyMatch(e -> e.getExtension().equalsIgnoreCase(ext));
  }
}
