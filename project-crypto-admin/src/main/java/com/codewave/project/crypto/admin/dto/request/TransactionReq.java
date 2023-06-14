package com.codewave.project.crypto.admin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionReq {
  
  private String tranType;

  private String serviceVersion;

  private String serviceEndpoint;

}
