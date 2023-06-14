package com.codewave.project.crypto.admin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChannelReq {

  private String channelCode;

  private String channelDomain;

}
