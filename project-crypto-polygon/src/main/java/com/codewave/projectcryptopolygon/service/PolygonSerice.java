package com.codewave.projectcryptopolygon.service;

import java.util.List;

import com.codewave.projectcryptopolygon.infra.exception.BusinessException;
import com.codewave.projectcryptopolygon.model.CoinExchange;

public interface PolygonSerice {

  List<CoinExchange> getCoinExchangeList();
}
