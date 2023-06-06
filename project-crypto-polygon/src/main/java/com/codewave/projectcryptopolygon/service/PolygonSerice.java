package com.codewave.projectcryptopolygon.service;

import java.util.List;

import com.codewave.projectcryptopolygon.infra.exception.BusinessException;
import com.codewave.projectcryptopolygon.model.CoinExchange;
import com.codewave.projectcryptopolygon.model.CoinExchange.ExchangeResult;

public interface PolygonSerice {

  List<ExchangeResult> getCoinExchangeList() throws BusinessException;
}
