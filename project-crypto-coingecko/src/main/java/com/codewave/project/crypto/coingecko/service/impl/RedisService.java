package com.codewave.project.crypto.coingecko.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.codewave.project.crypto.coingecko.infra.util.RedisUtil;
import com.codewave.project.crypto.coingecko.model.CoinMarketResp;
import com.codewave.project.crypto.coingecko.model.SimplePriceResp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RedisService {

  private final String coinMarketRedisKey = "crypto-coingecko:coin:market";

  private final String simplePriceRedisKey = "crypto-coingecko:simple:price";

  @Autowired
  RedisUtil<Object> redisUtil;

  @Autowired
  ObjectMapper objectMapper;

  public CoinMarketResp[] getCoinMarketResp() {
    try {
      String str = objectMapper.writeValueAsString(redisUtil.get(coinMarketRedisKey)); // Object -> String
      return objectMapper.readValue(str, CoinMarketResp[].class);
    } catch (JsonProcessingException e) {
      return new CoinMarketResp[] {};
    }
  }

  public SimplePriceResp getSimplePriceResp() {
    try {
      String str = objectMapper.writeValueAsString(redisUtil.get(simplePriceRedisKey)); // Object -> String
      return objectMapper.readValue(str, SimplePriceResp.class);
    } catch (JsonProcessingException e) {
      return new SimplePriceResp();
    }
  }

  @Async
  public void setCoinMarketResp(CoinMarketResp[] coinMarkets) {
    redisUtil.set(coinMarketRedisKey, coinMarkets, 604800000);
  }

  @Async
  public void setSimplePriceResp(SimplePriceResp simplePriceResp) {
    redisUtil.set(simplePriceRedisKey, simplePriceResp, 604800000);
  }

}
