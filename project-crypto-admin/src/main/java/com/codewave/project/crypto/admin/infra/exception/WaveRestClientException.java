package com.codewave.project.crypto.admin.infra.exception;

public class WaveRestClientException extends BusinessException {

  public WaveRestClientException() {
    super(BizCode.REST_CLIENT_EXCEPTION.getCode(), BizCode.REST_CLIENT_EXCEPTION.getMessage());
  }

}
