package com.reservation.movie.reservation.execption;

import com.reservation.movie.execption.BusinessException;
import com.reservation.movie.execption.ErrorCode;

public class ReservationException extends BusinessException {
  public ReservationException(ErrorCode errorCode) {
    super(errorCode);
  }

  public ReservationException(ErrorCode errorCode, String detailMessage) {
    super(errorCode, detailMessage);
  }
}
