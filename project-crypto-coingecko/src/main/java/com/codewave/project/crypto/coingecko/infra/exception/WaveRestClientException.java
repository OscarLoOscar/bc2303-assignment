package com.codewave.project.crypto.coingecko.infra.exception;

public class WaveRestClientException extends BusinessException {

  public WaveRestClientException() {
    super(BizCode.REST_CLIENT_EXCEPTION.getCode(), BizCode.REST_CLIENT_EXCEPTION.getMessage());
  }

}
