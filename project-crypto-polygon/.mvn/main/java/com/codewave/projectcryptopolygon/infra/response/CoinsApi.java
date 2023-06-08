package com.codewave.projectcryptopolygon.infra.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.codewave.projectcryptopolygon.infra.exception.BusinessException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CoinsApi {

  @Autowired
  RestTemplate restTemplate;


  public <T> T invoke(String domain,
      String endpoint,
      String pathSegment, HashMap<String, String> queryParms,
      Class<T> returnType) throws BusinessException { 
    try {
      UriComponentsBuilder url = UriComponentsBuilder.fromUriString(domain)
          .pathSegment(endpoint) 
          .path(pathSegment);

      for (Map.Entry<String, String> entry : queryParms.entrySet()) {
        url = url.queryParam(entry.getKey(), entry.getValue());
      }
      String urlString = url.build().toString();

      log.info("url={}", urlString);

      // invoke coingecko api with pre-defined return type (CoinsMarkats)
      return restTemplate.getForObject(urlString, returnType);
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException(80001, "Call coinGecko service fail.");
    }
  }

}