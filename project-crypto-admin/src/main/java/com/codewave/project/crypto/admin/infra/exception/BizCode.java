package com.codewave.project.crypto.admin.infra.exception;

import lombok.Getter;

@Getter
public enum BizCode {

  // 20000
  SUCCESS(20000, true, "SUCCESS"),
  // Fail 40000 - 49999
  AASTOCK_TIMEOUT(40001, false, "Connect AASTOCK TIMEOUT"),
  RESOURCE_NOT_FOUND(40002, false, "Resource Not Found"),
  REST_CLIENT_EXCEPTION(40003, false, "RestTemplate Api Call Fail"),
  RESOURCE_ACCESS_EXCEPTION(40004, false, "Resource Access Exception"),
  DEFAULT(99999, false, "Unknown") //
  ;

  private int code;
  private boolean success;
  private String message;

  private BizCode(int code, boolean success, String message) {
    this.code = code;
    this.success = success;
    this.message = message;
  }

}
