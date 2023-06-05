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

  @Override
  public List<CoinExchange> getCoinExchangeList() {
    try {
      List<String> params = new ArrayList<>(Arrays.asList(null, "usd"));
      log.info("TEST "+ params.toString());
      CoinExchange[] coins = restTemplate.getForObject(polygonCoinUrl, CoinExchange[].class, params.toArray());
      redisService.setCoinMarketResp(coins);
      log.info("ABC " + coins.toString());
      return Arrays.asList(coins);
    } catch (RestClientException e) {
      return Arrays.asList(redisService.getCoinMarketResp());
    }
  }

}
