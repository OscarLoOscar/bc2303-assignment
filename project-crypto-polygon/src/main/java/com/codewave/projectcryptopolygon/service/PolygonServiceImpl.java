package com.codewave.projectcryptopolygon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.codewave.projectcryptopolygon.infra.exception.BusinessException;
import com.codewave.projectcryptopolygon.model.CoinExchange;
import com.codewave.projectcryptopolygon.model.CoinExchange.Result;

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
  public List<Result> getCoinExchangeList()
      throws BusinessException {
    log.info("polygonUrl " + polygonUrl);
    List<Result> responseBody = restTemplate.getForObject(polygonUrl, List.class);
    return responseBody;
  }

  // correct
  // @Override
  // public List<Result> getMyObjects() throws Exception {
  // String jsonResponse = restTemplate.getForObject(polygonUrl, String.class);
  // log.info("Service jsonResponse " + jsonResponse);
  // ObjectMapper objectMapper = new ObjectMapper();
  // JsonNode root = objectMapper.readTree(jsonResponse);
  // log.info("service root : " + root);
  // if (root.isArray()) {
  // List<Result> result = objectMapper.readValue(root.traverse(), new
  // TypeReference<List<Result>>() {
  // });
  // log.info("1st result : " + result);
  // return result;

  // } else {
  // Result result = objectMapper.treeToValue(root, Result.class);
  // List<Result> Result = new ArrayList<>();
  // Result.add(result);
  // log.info("2nd result : " + result.toString());
  // log.info("3rd Result : " + Result.toString());
  // return Result.stream().collect(Collectors.toList());

  // }
  // }
  @Override
  public CoinExchange getMyObjects() throws Exception {
    CoinExchange jsonResponse = restTemplate.getForObject(polygonUrl, CoinExchange.class);
    log.info("jsonResponse" + jsonResponse);
    return jsonResponse;
  }

}
