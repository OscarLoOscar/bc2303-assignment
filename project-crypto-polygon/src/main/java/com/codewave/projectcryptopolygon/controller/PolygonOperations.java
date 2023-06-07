package com.codewave.projectcryptopolygon.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.codewave.projectcryptopolygon.infra.exception.BusinessException;
import com.codewave.projectcryptopolygon.model.CoinExchange.Results;

public interface PolygonOperations {

  @GetMapping(value = "/price")
  @ResponseStatus(value = HttpStatus.OK)
  List<Results> getExchangeRate() throws Exception ;
}
