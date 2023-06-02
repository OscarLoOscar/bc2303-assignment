package com.codewave.project.projectcryptocoingecko.service;

import java.util.List;
import java.util.Map;

import com.codewave.project.projectcryptocoingecko.infra.exception.BusinessException;
import com.codewave.project.projectcryptocoingecko.model.CoinsCurrency;
import com.codewave.project.projectcryptocoingecko.model.CoinsMarketDto;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.CoinMarketRespDto;
public interface CoinsService {

  List<CoinMarketRespDto> getAllData()  throws BusinessException ;

  Map<String,CoinsCurrency> getAllCoinsMarkets(List<String> cryptos,
  List<String> currencies) throws BusinessException;

}