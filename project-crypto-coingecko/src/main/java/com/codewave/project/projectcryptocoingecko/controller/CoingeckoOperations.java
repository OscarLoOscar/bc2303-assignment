package com.codewave.project.projectcryptocoingecko.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.codewave.project.projectcryptocoingecko.infra.exception.BusinessException;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.ChannelDto;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.CoinMarketRespDto;

public interface CoingeckoOperations {

  @GetMapping(value = "/coin/market")
  @ResponseStatus(value = HttpStatus.OK)
  List<CoinMarketRespDto> getAllCoinsMarketData();

  @GetMapping(value = "/coins/exchangerates")
  ChannelDto getExchangeRate(@RequestParam("cryptos") List<String> cryptos,
      @RequestParam("currencies") List<String> currencies)
      throws BusinessException;

  // @GetMapping(value = "/splitcomma")
  // public List<String> stringToList(@RequestParam("coins") List<String> coins);

}
