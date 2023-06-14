package com.codewave.project.crypto.channel.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.codewave.project.crypto.channel.service.ExchangeService;

@Service
public class CoinServiceFactory {

  @Autowired
  @Qualifier("coinExchangeService")
  private ExchangeService coinExchangeService;

  @Autowired
  @Qualifier("polyExchangeService")
  private ExchangeService polyExchangeService;

  public ExchangeService getExchangeService(String ChannelCode) {
    // Java 14 switch Lambda
    return switch (ChannelCode) {
      case "COINGECKO" -> coinExchangeService;
      case "POLYGON" -> polyExchangeService;
      default -> throw new IllegalStateException("Unexpected ChannelCode: " + ChannelCode);
    };
  }

}
