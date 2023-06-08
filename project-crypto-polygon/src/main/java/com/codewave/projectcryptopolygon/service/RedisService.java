package com.codewave.projectcryptopolygon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewave.projectcryptopolygon.infra.util.RedisUtil;
import com.codewave.projectcryptopolygon.model.CoinExchange.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
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

  public void setExchangeRate(Result[] coinMarkets) {
    log.info("step 3 : start setUsers");
    redisUtil.set(coinsMarketRedisKey, coinMarkets, 1204800000);
  }

  public Result[] getExchangeRate() {
    try {
      String str = objectMapper.writeValueAsString(redisUtil.get(coinsMarketRedisKey)); // Object -> String
      return objectMapper.readValue(str, Result[].class);
    } catch (NullPointerException | JsonProcessingException e) {
      return new Result[] {};
    }
  }
}
