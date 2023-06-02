package com.codewave.project.projectcryptocoingecko.infra.exception;

public class WaveResourceAccessException extends BusinessException {
  
  public WaveResourceAccessException() {
    super(BizCode.RESOURCE_ACCESS_EXCEPTION.getCode(), BizCode.RESOURCE_ACCESS_EXCEPTION.getMessage());
  }

}
