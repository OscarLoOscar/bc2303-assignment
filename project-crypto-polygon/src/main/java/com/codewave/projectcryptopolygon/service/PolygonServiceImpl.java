package com.codewave.projectcryptopolygon.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.codewave.projectcryptopolygon.infra.exception.BusinessException;
import com.codewave.projectcryptopolygon.infra.response.CoinsApi;
import com.codewave.projectcryptopolygon.model.CoinExchange;
import com.codewave.projectcryptopolygon.model.CoinExchange.ExchangeResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PolygonServiceImpl implements PolygonSerice {

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  RedisService redisService;

  @Autowired
  @Qualifier(value = "polygonCoinUrl")
  String polygonCoinUrl;

  // private CoinExchange getCoinExchange() {
  // return restTemplate.getForObject(polygonCoinUrl, CoinExchange.class);
  // }  
  @Override
  public List<ExchangeResult> getCoinExchangeList() throws BusinessException {
    log.info("polygonCoinUrl " + polygonCoinUrl);
    ExchangeResult[] coins = restTemplate.getForObject(polygonCoinUrl, ExchangeResult[].class);
    redisService.setExchangeRate(coins);
    log.info("ABC " + coins.toString());
    return Arrays.asList(coins);
  }

}
