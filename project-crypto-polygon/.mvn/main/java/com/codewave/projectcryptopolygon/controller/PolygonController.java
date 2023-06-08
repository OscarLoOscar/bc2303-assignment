package com.codewave.projectcryptopolygon.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewave.projectcryptopolygon.infra.exception.BusinessException;
import com.codewave.projectcryptopolygon.model.CoinExchange;
import com.codewave.projectcryptopolygon.model.CoinExchange.Result;
import com.codewave.projectcryptopolygon.service.PolygonSerice;

@RestController
@RequestMapping(value = "/crypto/v1/polygon")
public class PolygonController implements PolygonOperations {

  @Autowired
  PolygonSerice polygonSerice;

  @Autowired
  ModelMapper modelMapper;

  // @Override
  // public List<Result> getExchangeRate() throws BusinessException {
  //   List<Result> Result = polygonSerice.getCoinExchangeList();
  //   return Result;
  // }

  // correct
  // @Override
  // public List<Result> getExchangeRate() throws Exception {
  //   List<Result> Result = polygonSerice.getMyObjects();
  //   return Result;
  // }

  @Override
  public CoinExchange getExchangeRate() throws Exception {
    CoinExchange Result = polygonSerice.getMyObjects();
    return Result;
  }
}
