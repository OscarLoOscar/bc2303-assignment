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
import com.codewave.projectcryptopolygon.model.CoinExchange.Results;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PolygonServiceImpl implements PolygonSerice {

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  RedisService redisService;

  @Autowired
  @Qualifier(value = "polygonUrl")
  String polygonUrl;

  @Override
  public List<Results> getCoinExchangeList()
      throws BusinessException {
    log.info("polygonUrl " + polygonUrl);
    List<Results> responseBody = restTemplate.getForObject(polygonUrl, List.class);
    return responseBody;
  }

  @Override
  public List<Results> getMyObjects() throws Exception {
    String jsonResponse = restTemplate.getForObject(polygonUrl, String.class);
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode root = objectMapper.readTree(jsonResponse);

    if (root.isArray()) {
      List<Results> result = objectMapper.readValue(root.traverse(), new TypeReference<List<Results>>() {
      });
      return result;

    } else {
      Results result = objectMapper.treeToValue(root, Results.class);
      List<Results> results = new ArrayList<>();
      results.add(result);
      return results;

    }
  }

}
