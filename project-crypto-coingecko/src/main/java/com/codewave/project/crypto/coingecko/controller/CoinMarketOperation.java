package com.codewave.project.crypto.coingecko.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import com.codewave.project.crypto.coingecko.model.dto.CoinMarketRespDto;

public interface CoinMarketOperation {

  @GetMapping(value = "/coin/market")
  List<CoinMarketRespDto> getCoinMarket();

}
