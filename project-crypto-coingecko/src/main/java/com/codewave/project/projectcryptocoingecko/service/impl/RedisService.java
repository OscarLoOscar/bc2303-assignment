package com.codewave.project.projectcryptocoingecko.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewave.project.projectcryptocoingecko.infra.util.RedisUtil;
import com.codewave.project.projectcryptocoingecko.model.CoinsMarketResp;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.CoinMarketRespDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RedisService {

  private final String coinsMarketRedisKey = "crypto-coingecko:coin:market";
  @Autowired
  RedisUtil<Object> redisUtil;

  // @Autowired
  // RedisUtil<String> testRedisUtil;

  @Autowired
  ObjectMapper objectMapper;

  public void setCoinMarketResp(CoinsMarketResp[] coinMarkets) {
    log.info("start setUsers");
    redisUtil.set(coinsMarketRedisKey, coinMarkets, 604800000);
  }

  public CoinsMarketResp[] getCoinMarketResp()  {
    try {
      String str = objectMapper.writeValueAsString(redisUtil.get(coinsMarketRedisKey)); // Object -> String
      return objectMapper.readValue(str, CoinsMarketResp[].class);
    } catch (NullPointerException | JsonProcessingException e) {
      return new CoinsMarketResp[] {};
    }
  }

}
