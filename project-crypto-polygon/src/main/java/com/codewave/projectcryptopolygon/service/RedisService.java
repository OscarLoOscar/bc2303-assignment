package com.codewave.projectcryptopolygon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewave.projectcryptopolygon.infra.util.RedisUtil;
import com.codewave.projectcryptopolygon.model.CoinExchange;
import com.codewave.projectcryptopolygon.model.CoinExchange.ExchangeResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RedisService {

  private final String coinsMarketRedisKey = "crypto-polygon:simple";

  // private final String exchangeRateRedisKey = "crypto-coingecko:simple:price";

  @Autowired
  RedisUtil<Object> redisUtil;

  // @Autowired
  // RedisUtil<String> testRedisUtil;

  @Autowired
  ObjectMapper objectMapper;

  public void setExchangeRate(ExchangeResult[] coinMarkets) {
    log.info("step 3 : start setUsers");
    redisUtil.set(coinsMarketRedisKey, coinMarkets, 1204800000);
  }

  public ExchangeResult[] getExchangeRate() {
    try {
      String str = objectMapper.writeValueAsString(redisUtil.get(coinsMarketRedisKey)); // Object -> String
      return objectMapper.readValue(str, ExchangeResult[].class);
    } catch (NullPointerException | JsonProcessingException e) {
      return new ExchangeResult[] {};
    }
  }

  // public void setExchangeRate(List<ExchangeRate> exchangeRate) {
  //   log.info("start  setExchangeRate");
  //   redisUtil.set(coinsMarketRedisKey, exchangeRate, 1204800000);
  // }

  // public List<ExchangeRate> getExchangeRate() {
  //   try {
  //     String str = objectMapper.writeValueAsString(redisUtil.get(exchangeRateRedisKey));
  //     return objectMapper.readValue(str, new TypeReference<List<ExchangeRate>>() {
  //     });
  //   } catch (NullPointerException | JsonProcessingException e) {
  //     return new ArrayList<>();
  //   }
  // }
}
