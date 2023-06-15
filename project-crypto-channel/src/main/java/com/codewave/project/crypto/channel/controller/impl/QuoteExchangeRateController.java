package com.codewave.project.crypto.channel.controller.impl;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewave.project.crypto.channel.controller.QuoteExchangeRateOperation;
import com.codewave.project.crypto.channel.dto.response.Exchange.ExchangeReq;
import com.codewave.project.crypto.channel.dto.response.Exchange.ExchangeResp;
import com.codewave.project.crypto.channel.infra.enums.Currency;
import com.codewave.project.crypto.channel.service.QuoteService;

@RestController
@RequestMapping(value = "/crypto/channel/v1")
public class QuoteExchangeRateController implements QuoteExchangeRateOperation {

  @Autowired
  QuoteService quoteService;
  // @Override
  // public ExchangeResp getExchange(Currency curr1, Currency curr2) {
  // // Call the QuoteService to get the quote
  // ExchangeReq exchangeReq = quoteService.getQuote(curr1.toString(),
  // BigDecimal.ONE, curr2.toString());

  // // Create the ExchangeResp object
  // ExchangeResp exchangeResp = ExchangeResp.builder()
  // .fromCurr(exchangeReq.getFromCurr())
  // .fromCurrQuantity(exchangeReq.getFromCurrQuantity())
  // .toCurr(exchangeReq.getToCurr())
  // .toCurrQuantity(exchangeReq.getToCurrQuantity())
  // .build();

  // // Return the ExchangeResp object
  // return exchangeResp;
  // }

  /**
   * {
   * "fromCurr": "USD",
   * "fromCurrQuantity": 100,
   * "toCurr": "ETH"
   * }
   */
  @Override
  public ExchangeResp getExchange(ExchangeReq exchangeReq) {
    Currency fromCurr = exchangeReq.getFromCurr();
    BigDecimal fromCurrQuantity = exchangeReq.getFromCurrQuantity();
    Currency toCurr = exchangeReq.getToCurr();
    ExchangeReq exchangeReqWithQuote = quoteService.getQuote(fromCurr, fromCurrQuantity, toCurr);
    UUID uuid = UUID.randomUUID();
    // ExchangeResp exchangeResp = new ExchangeResp(uuid, fromCurr,
    // fromCurrQuantity, toCurr, exchangeReqWithQuote.getToCurrQuantity());
    ExchangeResp exchangeResp = new ExchangeResp(fromCurr, fromCurrQuantity, toCurr,
        exchangeReqWithQuote.getToCurrQuantity(), uuid);

    return exchangeResp;
  }
}
