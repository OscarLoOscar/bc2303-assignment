package com.codewave.project.crypto.channel.service;

import com.codewave.project.crypto.channel.dto.response.CoinServiceResp;
import com.codewave.project.crypto.channel.infra.enums.Source;
import com.codewave.project.crypto.channel.infra.enums.Trantype;
import com.codewave.project.crypto.channel.dto.response.ChannelResp;

public interface AdminService {
  
  ChannelResp getChannel(Source source, Trantype tranType);

  CoinServiceResp getCoinService(String channelCode, String tranType);

}
