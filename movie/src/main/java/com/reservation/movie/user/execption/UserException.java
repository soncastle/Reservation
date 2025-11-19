package com.reservation.movie.user.execption;

import com.reservation.movie.execption.BusinessException;
import com.reservation.movie.execption.ErrorCode;

public class UserException extends BusinessException {
  public UserException(ErrorCode errorCode, String detailMessage) {
    super(errorCode, detailMessage);
  }

  public UserException(ErrorCode errorCode) {
    super(errorCode);
  }
}
