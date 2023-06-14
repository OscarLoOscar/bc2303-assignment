package com.codewave.project.crypto.polygon.infra.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MueRestApi {

  private RestTemplate restTemplate;

  public MueRestApi(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public <T> ResponseEntity<T> get(String url, Class<T> responseType) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    List<MediaType> mediaTypeList = new ArrayList<>();
    mediaTypeList.add(MediaType.APPLICATION_JSON);
    headers.setAccept(mediaTypeList);

    HttpEntity<String> entity = new HttpEntity<>(headers);

    return restTemplate.exchange(url, HttpMethod.GET, entity, responseType);
  }
}
