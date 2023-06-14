package com.codewave.project.crypto.channel.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewave.project.crypto.channel.controller.ExchangeOperation;
import com.codewave.project.crypto.channel.controller.TradeOperation;
import com.codewave.project.crypto.channel.dto.response.ChannelDto;
import com.codewave.project.crypto.channel.dto.response.ChannelResp;
import com.codewave.project.crypto.channel.factory.CoinServiceFactory;
import com.codewave.project.crypto.channel.infra.enums.Currency;
import com.codewave.project.crypto.channel.infra.enums.Source;
import com.codewave.project.crypto.channel.infra.enums.Trantype;
import com.codewave.project.crypto.channel.service.AdminService;
import com.codewave.project.crypto.channel.service.ExchangeService;

@RestController
@RequestMapping(value = "/crypto/channel/web/v1")
public class CoinWebController implements ExchangeOperation, TradeOperation {

  @Autowired
  CoinServiceFactory factory;

  @Autowired
  AdminService adminService;

  @Override
  public ChannelDto getExchange(Currency curr1, Currency curr2) {
    ChannelResp channelResp = adminService.getChannel(Source.WEB, Trantype.EXCHANGE);
    ExchangeService exchangeService = factory.getExchangeService(channelResp.getChannelCode());
    return exchangeService.exchange(curr1, curr2);
  }

}
