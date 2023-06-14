package com.codewave.project.crypto.admin.mapper;

import com.codewave.project.crypto.admin.dto.response.ChannelResp;
import com.codewave.project.crypto.admin.dto.response.CoinServiceResp;
import com.codewave.project.crypto.admin.dto.response.TransactionResp;
import com.codewave.project.crypto.admin.infra.enums.UriScheme;
import com.codewave.project.crypto.admin.infra.util.ApiUtil;

public final class ChannelMapper {

  public static CoinServiceResp map(TransactionResp tranResp, ChannelResp channelResp) {
    // System.out.println(entity.getChannel().getChannelDomain());
    String serviceUrl = ApiUtil.uriBuilder(UriScheme.HTTP,
        channelResp.getChannelDomain(),
        tranResp.getServiceEndpoint(),
        tranResp.getServiceVersion())
        .build(false) // no encoding
        .toUriString();

    return CoinServiceResp.builder()
        .id(tranResp.getId())
        .channelId(channelResp.getId())
        .channelCode(channelResp.getChannelCode())
        .tranType(tranResp.getTranType())
        .serviceUrl(serviceUrl)
        .build();
  }

}
