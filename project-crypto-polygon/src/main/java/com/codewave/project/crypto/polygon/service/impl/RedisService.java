package com.codewave.project.crypto.polygon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.codewave.project.crypto.polygon.infra.util.RedisUtil;
import com.codewave.project.crypto.polygon.model.PreviousClose;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RedisService {

  private final String simplePriceRedisKey = "crypto-polygon:prev-close";

  @Autowired
  RedisUtil<Object> redisUtil;

  @Autowired
  ObjectMapper objectMapper;

  public PreviousClose getSimplePriceResp() {
    try {
      String str = objectMapper.writeValueAsString(redisUtil.get(simplePriceRedisKey)); // Object -> String
      return objectMapper.readValue(str, PreviousClose.class);
    } catch (JsonProcessingException e) {
      return new PreviousClose();
    }
  }

  @Async
  public void setSimplePriceResp(PreviousClose simplePriceResp) {
    redisUtil.set(simplePriceRedisKey, simplePriceResp, 604800000);
  }

}
