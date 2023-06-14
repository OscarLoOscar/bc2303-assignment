package com.codewave.project.crypto.channel.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter  // for modelmapper
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResp {

  private Long id;
  
  private String tranType;

  private String serviceVersion;

  private String serviceEndpoint;

}
