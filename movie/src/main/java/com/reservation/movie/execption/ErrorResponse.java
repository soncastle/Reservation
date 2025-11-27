package com.reservation.movie.execption;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
  private final boolean success;
  private final int status;
  private final String code;      // ErrorCode 이름
  private final String message;
  private final String detail;    // (선택) 추가 설명
  private final LocalDateTime timestamp;
  private final String path;      // (선택) 요청 URI

  public static ErrorResponse of(ErrorCode errorCode, String detail, String path) {
    return ErrorResponse.builder()
        .success(false)
        .status(errorCode.getStatus().value())
        .code(errorCode.name())
        .message(errorCode.getMessage())
        .detail(detail)
        .timestamp(LocalDateTime.now())
        .path(path)
        .build();
  }

  public static ErrorResponse of(ErrorCode errorCode) {
    return of(errorCode, null, null);
  }
}
