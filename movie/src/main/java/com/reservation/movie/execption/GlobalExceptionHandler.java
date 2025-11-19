package com.reservation.movie.execption;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice //모든 @restController 대상!
public class GlobalExceptionHandler {

  // 우리 커스텀 비즈니스 예외 처리
  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorResponse> handleBusinessException(
      BusinessException e,
      HttpServletRequest request
  ) {
    ErrorCode errorCode = e.getErrorCode();
    log.warn("[BusinessException] {} - {}", errorCode.name(), e.getMessage());

    ErrorResponse body = ErrorResponse.of(errorCode, e.getMessage(), request.getRequestURI());
    return ResponseEntity
        .status(errorCode.getStatus())
        .body(body);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponse> handleDBConstraint(DataIntegrityViolationException e, HttpServletRequest request) {

    log.error("[동시성충돌] 좌석 중복 예약 시도 발생", e);

    return ResponseEntity
        .status(ErrorCode.SEAT_ALREADY_RESERVED.getStatus())
        .body(ErrorResponse.of(ErrorCode.SEAT_ALREADY_RESERVED));
//        .status(HttpStatus.CONFLICT)
//        .body(ErrorResponse.of(ErrorCode.SEAT_ALREADY_RESERVED, "데이터베이스 제약 조건 위반 - 동시성", request.getRequestURI()));
  }


  // 그 외 처리 안 한 모든 예외
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(
      Exception e,
      HttpServletRequest request
  ) {
    log.error("[Exception] ", e);
    ErrorResponse body = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage(), request.getRequestURI());
    return ResponseEntity
        .status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus())
        .body(body);
  }
}
