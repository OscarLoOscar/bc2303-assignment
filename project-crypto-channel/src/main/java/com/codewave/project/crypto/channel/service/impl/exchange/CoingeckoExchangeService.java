package com.codewave.project.crypto.channel.service.impl.exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.codewave.project.crypto.channel.dto.response.ChannelDto;
import com.codewave.project.crypto.channel.infra.enums.Currency;
import com.codewave.project.crypto.channel.service.AdminService;
import com.codewave.project.crypto.channel.service.ExchangeService;

@Service
@Qualifier("coinExchangeService")
@Primary
public class CoingeckoExchangeService implements ExchangeService {

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  AdminService adminService;

  @Override
  public ChannelDto exchange(Currency curr1, Currency curr2) {
    String url = adminService.getCoinService("COINGECKO", "EXCHANGE").getServiceUrl();
    // Add PathVariables
    url = url.concat("/").concat(curr1.name()).concat("/").concat(curr2.name());
    // Get Exchange rates
    return restTemplate.getForObject(url, ChannelDto.class);
  }

}
