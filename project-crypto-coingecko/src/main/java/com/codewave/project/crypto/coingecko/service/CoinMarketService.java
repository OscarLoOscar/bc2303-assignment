package com.codewave.project.crypto.coingecko.service;

import java.util.List;

import com.codewave.project.crypto.coingecko.model.CoinMarketResp;

public interface CoinMarketService {

  public List<CoinMarketResp> getCoinMarketWithRedis();

}
