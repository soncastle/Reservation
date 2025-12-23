package com.reservation.movie.execption;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorResponse> handleBusinessException(
      BusinessException e,
      HttpServletRequest request
  ) {
    ErrorCode errorCode = e.getErrorCode();
    log.warn("[BusinessException] {} - {} ({})", errorCode.name(), e.getMessage(), request.getRequestURI(), e);

    return ResponseEntity
        .status(errorCode.getStatus())
        .body(ErrorResponse.of(errorCode, e.getMessage(), request.getRequestURI()));
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponse> handleDBConstraint(DataIntegrityViolationException e, HttpServletRequest request) {
    Throwable cause = e.getMostSpecificCause();
    String message = cause.getMessage();
    ErrorCode errorCode;

    if(message.contains("uk_user_email")){
      errorCode = ErrorCode.EMAIL_DUPLICATION;
      log.error("[동시성 충돌] 이메일 중복", e);
    }
     else {
      errorCode = ErrorCode.SEAT_ALREADY_RESERVED;
      log.error("[동시성 충돌] 좌석 외", e);
    }

    return ResponseEntity
        .status(errorCode.getStatus())
        .body(ErrorResponse.of(errorCode, errorCode.getMessage(), request.getRequestURI()));
  }

  // 그 외 처리 안 한 모든 예외
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(
      Exception e,
      HttpServletRequest request
  ) {
    ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;

    log.error("[Exception] {} - {} ({})", errorCode.name(), e.getMessage(), request.getRequestURI(), e);

    return ResponseEntity
        .status(errorCode.getStatus())
        .body(ErrorResponse.of(errorCode, e.getMessage(), request.getRequestURI()));
  }
}