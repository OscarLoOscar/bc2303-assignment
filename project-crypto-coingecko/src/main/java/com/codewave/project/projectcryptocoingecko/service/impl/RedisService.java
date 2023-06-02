package com.codewave.project.projectcryptocoingecko.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewave.project.projectcryptocoingecko.infra.exception.BusinessException;
import com.codewave.project.projectcryptocoingecko.infra.util.RedisUtil;
import com.codewave.project.projectcryptocoingecko.model.CoinsMarketDto;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.CoinMarketRespDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RedisService {

  private final String coinsRedisKey = "project-crypto-coingecko:coingecko:coins";
  @Autowired
  RedisUtil<Object> redisUtil;

  // @Autowired
  // RedisUtil<String> testRedisUtil;

  @Autowired
  ObjectMapper objectMapper;

  public void setCoins(CoinMarketRespDto[] coins) {
    redisUtil.set(coinsRedisKey, coins, 1200000);
  }

  public CoinMarketRespDto[] getCoins() throws BusinessException {
    try {
      String str = objectMapper.writeValueAsString(redisUtil.get(coinsRedisKey)); // Object -> String
      return objectMapper.readValue(str, CoinMarketRespDto[].class);
    } catch (NullPointerException | JsonProcessingException e) {
      return new CoinMarketRespDto[] {};
    }
  }

  // public void set(String key, String value) {
  // testRedisUtil.set(key, value, 120000);
  // }

  // public Object get(String key) {
  // return testRedisUtil.get(key);
  // }

}
