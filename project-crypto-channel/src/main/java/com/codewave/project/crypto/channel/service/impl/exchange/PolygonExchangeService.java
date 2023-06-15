package com.codewave.project.crypto.channel.service.impl.exchange;

import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.codewave.project.crypto.channel.dto.response.ChannelDto;
import com.codewave.project.crypto.channel.dto.response.ChannelDto.ExchangeRate;
import com.codewave.project.crypto.channel.infra.enums.Currency;
import com.codewave.project.crypto.channel.service.AdminService;
import com.codewave.project.crypto.channel.service.ExchangeService;

@Service
@Qualifier("polyExchangeService")
public class PolygonExchangeService implements ExchangeService {

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  AdminService adminService;

  @Override
  public ChannelDto exchange(Currency curr1, Currency curr2) {
    String url = adminService.getCoinService("POLYGON", "EXCHANGE").getServiceUrl();
    // Add PathVariable
    url = url.concat("/").concat(curr1.name()).concat("/").concat(curr2.name());
    // Get Exchange rates
    return restTemplate.getForObject(url, ChannelDto.class);
  }
}
