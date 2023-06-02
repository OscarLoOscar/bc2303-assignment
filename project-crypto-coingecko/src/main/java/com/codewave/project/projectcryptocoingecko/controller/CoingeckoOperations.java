package com.codewave.project.projectcryptocoingecko.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.codewave.project.projectcryptocoingecko.infra.exception.BusinessException;
import com.codewave.project.projectcryptocoingecko.infra.response.ApiResponse;
import com.codewave.project.projectcryptocoingecko.model.ChannelDto;
import com.codewave.project.projectcryptocoingecko.model.CoinsMarketDto;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.CoinMarketRespDto;

public interface CoingeckoOperations {

  @GetMapping(value = "/coin/market")
  @ResponseStatus(value = HttpStatus.OK)
  ApiResponse<List<CoinMarketRespDto>> getAllCoinsMarketData() throws BusinessException;
//http://localhost:8086/crpyto/v1/coingecko/coin/market
  // https://api.coingecko.com/api/v3/exchange_rates
  @GetMapping(value = "/coins/exchangerates")
  ChannelDto getExchangeRate(@RequestParam("coins") List<String> cryptos,
      @RequestParam("currency") List<String> currencies)
      throws BusinessException;

  @GetMapping(value = "/splitcomma")
  public List<String> stringToList(@RequestParam("coins") List<String> coins);

}
