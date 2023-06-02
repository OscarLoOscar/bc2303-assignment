package com.codewave.project.projectcryptocoingecko.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewave.project.projectcryptocoingecko.controller.CoingeckoOperations;
import com.codewave.project.projectcryptocoingecko.infra.exception.BusinessException;
import com.codewave.project.projectcryptocoingecko.infra.response.ApiResponse;
import com.codewave.project.projectcryptocoingecko.infra.response.CoinsApi;
import com.codewave.project.projectcryptocoingecko.model.ChannelDto;
import com.codewave.project.projectcryptocoingecko.model.CoinsMarketDto;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.CoinMarketRespDto;
import com.codewave.project.projectcryptocoingecko.service.CoinsService;

//http://localhost:8085/crpyto/v1/coingecko/coin/market
@RestController
@RequestMapping(value = "/crpyto/v1/coingecko")
public class CoingeckoController implements CoingeckoOperations {
  @Autowired
  CoinsService coinsService;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public ApiResponse<List<CoinMarketRespDto>> getAllCoinsMarketData() throws BusinessException {
    List<CoinMarketRespDto> allCoins = coinsService.getAllData().stream()
        .map(e -> modelMapper.map(e, CoinMarketRespDto.class))
        .collect(Collectors.toList());
    return ApiResponse.<List<CoinMarketRespDto>>builder()
        .ok()
        .data(allCoins)
        .build();

  }

  @Override
  public ChannelDto getExchangeRate(List<String> cryptos,
      List<String> currencies) throws BusinessException {
    return ChannelDto.builder()
        .exchangeRates(CoinsApi.map(coinsService.getAllCoinsMarkets(cryptos, currencies)))
        .build();
  }

  @Override
  public List<String> stringToList(List<String> coins) {
    return coins;
  }
}
