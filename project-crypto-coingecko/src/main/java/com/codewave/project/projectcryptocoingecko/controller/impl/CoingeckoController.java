package com.codewave.project.projectcryptocoingecko.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewave.project.projectcryptocoingecko.controller.CoingeckoOperations;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.CoinMarketRespDto;
import com.codewave.project.projectcryptocoingecko.service.CoinsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/crypto/v1/coingecko")
public class CoingeckoController implements CoingeckoOperations {

  @Autowired
  CoinsService coinsService;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public List<CoinMarketRespDto> getAllCoinsMarketData() {
    return coinsService.getAllData().stream()
        .map(e -> modelMapper.map(e, CoinMarketRespDto.class))
        .collect(Collectors.toList());
  }

  // @Override
  // public ChannelDto getExchangeRate(List<String> cryptos,
  // List<String> currencies) throws BusinessException {
  // return ChannelDto.builder()
  // .exchangeRates(CoinsApi.map(coinsService.getAllCoinsMarkets(cryptos,
  // currencies)))
  // .build();
  // }

  // @Override
  // public List<String> stringToList(List<String> coins) {
  // return coins;
  // }
}
