package com.codewave.projectcryptopolygon.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewave.projectcryptopolygon.infra.exception.BusinessException;
import com.codewave.projectcryptopolygon.model.CoinExchange;
import com.codewave.projectcryptopolygon.service.PolygonSerice;

@RestController
@RequestMapping(value = "/crypto/polygon/api/v1")
public class PolygonController implements PolygonOperations {

  @Autowired
  PolygonSerice polygonSerice;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public List<CoinExchange> getExchangeRate() throws BusinessException {
    return polygonSerice.getCoinExchangeList().stream()
        .map(e -> modelMapper.map(e, CoinExchange.class))
        .collect(Collectors.toList());
  }

}
