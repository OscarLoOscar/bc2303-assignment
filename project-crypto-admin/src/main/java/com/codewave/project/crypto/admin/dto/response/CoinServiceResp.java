package com.codewave.project.crypto.admin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO should be immutable.
 * 
 * @author vincent.lau
 */
@Getter
@Setter  // for modelmapper
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoinServiceResp {

  private Long id;

  private Long channelId;

  private String channelCode;

  private String tranType;

  private String serviceUrl;

}
