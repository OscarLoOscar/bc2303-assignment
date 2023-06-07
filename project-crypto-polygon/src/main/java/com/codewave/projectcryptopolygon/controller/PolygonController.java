package com.codewave.projectcryptopolygon.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewave.projectcryptopolygon.infra.exception.BusinessException;
import com.codewave.projectcryptopolygon.model.CoinExchange.Results;
import com.codewave.projectcryptopolygon.service.PolygonSerice;

@RestController
@RequestMapping(value = "/crypto/v1/polygon")
public class PolygonController implements PolygonOperations {

  @Autowired
  PolygonSerice polygonSerice;

  @Autowired
  ModelMapper modelMapper;

  // @Override
  // public List<Results> getExchangeRate() throws BusinessException {
  //   List<Results> Results = polygonSerice.getCoinExchangeList();
  //   return Results;
  // }

  @Override
  public List<Results> getExchangeRate() throws Exception {
    List<Results> Results = polygonSerice.getMyObjects();
    return Results;
  }

}
