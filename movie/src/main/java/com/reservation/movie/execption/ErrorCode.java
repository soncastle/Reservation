package com.reservation.movie.execption;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {



  // 공통
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다."),
  INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "요청 값이 잘못되었습니다."),

  // 회원
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 회원을 찾을 수 없습니다."),
  EMAIL_DUPLICATION(HttpStatus.CONFLICT, "이미 사용 중인 이메일입니다."),
  INVALID_LOGIN(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호가 틀렸습니다."),

  // 예약
  RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 예약을 찾을 수 없습니다."),
  SEAT_ALREADY_RESERVED(HttpStatus.CONFLICT, "이미 예약된 좌석이 포함되어 있습니다.");

  private final HttpStatus status;
  private final String message;


  ErrorCode(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }
}
