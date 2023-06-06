package com.codewave.project.projectcryptocoingecko.service;

import java.util.HashMap;
import java.util.List;

import com.codewave.project.projectcryptocoingecko.infra.exception.BusinessException;
import com.codewave.project.projectcryptocoingecko.model.CoinsMarketResp;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.CoinsCurrency;

public interface CoinsService {

  List<CoinsMarketResp> getAllData();

  HashMap<String, CoinsCurrency> getSimplePricesByMap(List<String> cryptos,
      List<String> currencies) throws BusinessException;

  HashMap<String, List<String>> getExchangeRates(List<String> cryptos,
      List<String> currencies) throws BusinessException;

}