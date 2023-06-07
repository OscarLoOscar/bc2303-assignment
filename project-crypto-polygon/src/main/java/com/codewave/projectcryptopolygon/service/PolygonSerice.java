package com.codewave.projectcryptopolygon.service;

import java.util.List;

import com.codewave.projectcryptopolygon.infra.exception.BusinessException;
import com.codewave.projectcryptopolygon.model.CoinExchange.Results;

public interface PolygonSerice {

  List<Results> getCoinExchangeList() throws BusinessException;

  //处理REST API响应并将其反序列化为Java对象或Java对象列表
  List<Results> getMyObjects() throws Exception;
}
