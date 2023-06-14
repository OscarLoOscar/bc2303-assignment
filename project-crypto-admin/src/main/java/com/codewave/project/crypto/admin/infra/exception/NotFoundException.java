package com.codewave.project.crypto.admin.infra.exception;

/**
 * Checked Exception
 */
public class NotFoundException extends BusinessException {

  public NotFoundException() {
    super(BizCode.RESOURCE_NOT_FOUND.getCode(), BizCode.RESOURCE_NOT_FOUND.getMessage());
  }
  
}
