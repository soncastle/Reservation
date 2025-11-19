package com.reservation.movie.execption;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{ //원래 자바 자체가 하는 전역 예외를 여기서 처리?
  private final ErrorCode errorCode;

  public BusinessException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  public BusinessException(ErrorCode errorCode, String detailMessage) {
    super(detailMessage);
    this.errorCode = errorCode;
  }
}