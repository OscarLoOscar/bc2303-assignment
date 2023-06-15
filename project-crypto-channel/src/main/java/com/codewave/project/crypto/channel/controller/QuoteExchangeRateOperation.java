package com.codewave.project.crypto.channel.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.codewave.project.crypto.channel.dto.response.Exchange.ExchangeReq;
import com.codewave.project.crypto.channel.dto.response.Exchange.ExchangeResp;

public interface QuoteExchangeRateOperation {
  @PostMapping(value = "/quote")
  ExchangeResp getExchange(@RequestBody ExchangeReq exchangeReq);

}
