package com.codewave.project.crypto.channel.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO should be immutable.
 * 
 * @author vincent.lau
 */
@Getter
@Setter // for modelmapper
@AllArgsConstructor
@NoArgsConstructor
public class ChannelResp {
  
  private Long id;

  private String channelCode;

  private String channelDomain;

  private List<TransactionResp> transactions;

}
