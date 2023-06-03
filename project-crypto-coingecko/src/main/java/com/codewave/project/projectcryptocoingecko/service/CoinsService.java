package com.codewave.project.projectcryptocoingecko.service;

import java.util.List;
import com.codewave.project.projectcryptocoingecko.model.CoinsMarketResp;

public interface CoinsService {

  List<CoinsMarketResp> getAllData() ;

  // Map<String,CoinsCurrency> getAllCoinsMarkets(List<String> cryptos,
  // List<String> currencies) throws BusinessException;

}