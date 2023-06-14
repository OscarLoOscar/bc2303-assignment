package com.codewave.project.crypto.channel.service.impl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.codewave.project.crypto.channel.dto.response.ChannelResp;
import com.codewave.project.crypto.channel.dto.response.CoinServiceResp;
import com.codewave.project.crypto.channel.infra.enums.Source;
import com.codewave.project.crypto.channel.infra.enums.Trantype;
import com.codewave.project.crypto.channel.infra.enums.UriScheme;
import com.codewave.project.crypto.channel.infra.util.ApiUtil;
import com.codewave.project.crypto.channel.service.AdminService;

@Service
public class AdminServiceHolder implements AdminService {

  @Autowired
  private RestTemplate restTemplate;

  @Value(value = "${api.crypto.admin.domain}")
  private String domain;

  @Value(value = "${api.crypto.admin.endpoints.channel.version}")
  private String channelVersion;

  @Value(value = "${api.crypto.admin.endpoints.channel.endpoint}")
  private String channelEndpoint;

  @Value(value = "${api.crypto.admin.endpoints.transaction.version}")
  private String transactionVersion;

  @Value(value = "${api.crypto.admin.endpoints.transaction.endpoint}")
  private String transactionEndpoint;

  @Override
  public ChannelResp getChannel(Source source, Trantype tranType) {
    // Construct query parameters
    MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
    queryParams.add("source" , source.toString());
    queryParams.add("tranType", tranType.toString());
    // Construct URL
    String url = ApiUtil.uriBuilder(UriScheme.HTTP, domain, channelEndpoint,
        queryParams, channelVersion).build(false).toUriString();
    System.out.println("url=" + url);
    // Call admin service to get Channel
    return restTemplate.getForObject(url, ChannelResp.class);
  }

  @Override
  public CoinServiceResp getCoinService(String channelCode, String tranType) {
    // Construct query parameters
    MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
    queryParams.add("channelCode", channelCode);
    queryParams.add("tranType", tranType);
    // Construct URL
    String url = ApiUtil.uriBuilder(UriScheme.HTTP, domain, transactionEndpoint,
        queryParams, transactionVersion).build(false).toUriString();
    System.out.println("url=");
    // Call admin service to get Channel
    return restTemplate.getForObject(url, CoinServiceResp.class);
  }

}
