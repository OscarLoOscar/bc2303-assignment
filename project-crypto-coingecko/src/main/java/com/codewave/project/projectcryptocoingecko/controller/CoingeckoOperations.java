package com.codewave.project.projectcryptocoingecko.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.codewave.project.projectcryptocoingecko.infra.exception.BusinessException;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.CoinMarketRespDto;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.CoinsCurrency;

public interface CoingeckoOperations {

  @GetMapping(value = "/coin/market")
  @ResponseStatus(value = HttpStatus.OK)
  List<CoinMarketRespDto> getAllCoinsMarketData();

  // @GetMapping(value = "/coins/exchangerates")
  // ChannelDto getExchangeRate(@RequestParam("cryptos") List<String> cryptos,
  // @RequestParam("currencies") List<String> currencies)
  // throws BusinessException;

  // @GetMapping(value = "/coins/exchangerates")
  // List<ExchangeRate> getExchangeRate(@RequestParam("cryptos") List<String>
  // cryptos,
  // @RequestParam("currencies") List<String> currencies)
  // throws BusinessException;

  @GetMapping(value = "/coins/exchangerates")
  @ResponseStatus(value = HttpStatus.OK)
  HashMap<String, HashMap<String, CoinsCurrency>> getExchangeRate(@RequestParam("cryptos") List<String> cryptos,
      @RequestParam("currencies") List<String> currencies)
      throws BusinessException;

  // @GetMapping(value = "/splitcomma")
  // public List<String> stringToList(@RequestParam("coins") List<String> coins);

}
