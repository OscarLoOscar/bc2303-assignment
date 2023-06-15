package com.codewave.project.crypto.channel.service;

import java.math.BigDecimal;

import com.codewave.project.crypto.channel.dto.response.Exchange.ExchangeReq;
import com.codewave.project.crypto.channel.infra.enums.Currency;

public interface QuoteService {
  ExchangeReq getQuote( Currency fromCurr, BigDecimal fromCurrQuantity, Currency toCurr);
}
