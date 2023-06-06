package com.codewave.project.projectcryptocoingecko.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codewave.project.projectcryptocoingecko.infra.exception.BusinessException;
import com.codewave.project.projectcryptocoingecko.model.CoinsMarketResp;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.CoinsCurrency;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.ChannelDto.ExchangeRate;

public interface CoinsService {

  List<CoinsMarketResp> getAllData();

  HashMap<String, CoinsCurrency> getSimplePricesByMap(List<String> cryptos,
      List<String> currencies) throws BusinessException;

  HashMap<String, List<String>> getExchangeService(List<String> cryptos,
      List<String> currencies) throws BusinessException;

}