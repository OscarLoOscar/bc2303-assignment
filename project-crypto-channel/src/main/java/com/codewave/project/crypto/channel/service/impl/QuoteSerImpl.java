package com.codewave.project.crypto.channel.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.codewave.project.crypto.channel.dto.response.ChannelDto;
import com.codewave.project.crypto.channel.dto.response.Exchange.ExchangeReq;
import com.codewave.project.crypto.channel.infra.enums.Currency;
import com.codewave.project.crypto.channel.service.ExchangeService;
import com.codewave.project.crypto.channel.service.QuoteService;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuoteSerImpl implements QuoteService {

  @Autowired
  RedisTemplate<String, ExchangeReq> redisTemplate;

  @Autowired
  ExchangeService exchangeService;

  @Override
  public ExchangeReq getQuote(Currency fromCurr, BigDecimal fromCurrQuantity, Currency toCurr) {
    // Generate a UUID for the response
    UUID uuid = UUID.randomUUID();
    // Call the exchange method of the ExchangeService to get the exchange rate ,記，學
    ChannelDto channelDto = exchangeService.exchange(fromCurr, toCurr);
    // Get the exchange rate from the ChannelDto object
    BigDecimal exchangeRateValue = channelDto.getExchangeRates().get(0).getRate().setScale(2, RoundingMode.HALF_UP);
    log.info("Before exchangeRateValue " + exchangeRateValue.toString());
    if (!fromCurr.isCrypto()) {
      exchangeRateValue = BigDecimal.ONE.divide(exchangeRateValue,10, RoundingMode.HALF_UP);
      log.info("USD to crypto exchangeRateValue " + exchangeRateValue.toString());
      BigDecimal toCurrQuantity = fromCurrQuantity.multiply(exchangeRateValue);
      ExchangeReq exchangeReq = new ExchangeReq(fromCurr, fromCurrQuantity, toCurr, toCurrQuantity);
      redisTemplate.opsForValue().set(uuid.toString(), exchangeReq, Duration.ofMinutes(10));
      return exchangeReq;
    } else {
      // Calculate the toCurrQuantity
      log.info("exchangeRateValue " + exchangeRateValue.toString());
      BigDecimal toCurrQuantity = fromCurrQuantity.multiply(exchangeRateValue);
      // Create the ExchangeReq object
      ExchangeReq exchangeReq = new ExchangeReq(fromCurr, fromCurrQuantity, toCurr, toCurrQuantity);
      // Store the ExchangeReq object in Redis with a 10-minute expiry
      redisTemplate.opsForValue().set(uuid.toString(), exchangeReq, Duration.ofMinutes(10));
      // Return the ExchangeReq object
      return exchangeReq;
    }

  }
}