package com.codewave.project.crypto.admin.service;

import com.codewave.project.crypto.admin.dto.request.ChannelReq;
import com.codewave.project.crypto.admin.dto.response.ChannelResp;
import com.codewave.project.crypto.admin.infra.enums.Source;

public interface ChannelService {
  
  ChannelResp findChannel(Long id);
  
  ChannelResp findChannel(String channelCode);

  ChannelResp findChannel(String source, String tranType);

  ChannelResp saveChannel(ChannelReq channel);

}
