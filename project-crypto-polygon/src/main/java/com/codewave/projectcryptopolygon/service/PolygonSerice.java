package com.codewave.projectcryptopolygon.service;

import java.util.List;

import com.codewave.projectcryptopolygon.infra.exception.BusinessException;
import com.codewave.projectcryptopolygon.model.CoinExchange;
import com.codewave.projectcryptopolygon.model.CoinExchange.Result;

public interface PolygonSerice {

  List<Result> getCoinExchangeList() throws BusinessException;

  //处理REST API响应并将其反序列化为Java对象或Java对象列表
  //List<Result> getMyObjects() throws Exception;

  CoinExchange getMyObjects() throws Exception;
}
