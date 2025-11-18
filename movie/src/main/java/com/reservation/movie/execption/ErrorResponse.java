package com.reservation.movie.execption;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
  private final int status;       // HTTP status code (ex. 400, 404)
  private final String code;      // ErrorCode 이름 (ex. USER_NOT_FOUND)
  private final String message;   // 사용자에게 보여줄 메시지
  private final String detail;    // (선택) 추가 설명
  private final LocalDateTime timestamp; // 발생 시간
  private final String path;      // (선택) 요청 URI

  public static ErrorResponse of(ErrorCode errorCode, String detail, String path) {
    return ErrorResponse.builder()
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
