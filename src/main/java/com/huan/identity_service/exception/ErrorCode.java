package com.huan.identity_service.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
  UNCATEGORIZED_ERROR(9999, "Uncategorized Error"),
  INVALID_MESSAGE_KEY(1001, "Invalid Message Key"),
  USER_EXISTED(1002, "User existed"),
  USERNAME_INVALID(1003, "Username must bt at least 4 characters"),
  PASSWORD_INVALID(1004, "Password must bt at least 8 characters"),
  USER_NOT_EXISTED(1005, "User not existed"),
  UNAUTHENTICATED(1005, "Unauthenticated");

  private final int code;
  private final String message;

  ErrorCode(int code, String message) {
    this.code = code;
    this.message = message;
  }
}
