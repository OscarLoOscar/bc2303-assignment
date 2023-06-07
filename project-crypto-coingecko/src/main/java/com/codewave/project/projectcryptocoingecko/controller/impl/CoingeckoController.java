package com.codewave.project.projectcryptocoingecko.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewave.project.projectcryptocoingecko.controller.CoingeckoOperations;
import com.codewave.project.projectcryptocoingecko.infra.exception.BusinessException;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.CoinMarketRespDto;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.CoinsCurrency;
import com.codewave.project.projectcryptocoingecko.service.CoinsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/crypto/v1/coingecko")
@Slf4j
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

  @Override
  public HashMap<String, CoinsCurrency> getExchangeRate(List<String> cryptos, List<String> currencies)
      throws BusinessException {
    HashMap<String, CoinsCurrency> exchangeRates = coinsService.getExchangeService(cryptos,
        currencies);
    // Create a map from cryptocurrency name to CoinsCurrency object
    // HashMap <String, CoinsCurrency> resultMap = new HashMap<>();

    // Convert the map to the desired output format
    // HashMap<String, HashMap<String, CoinsCurrency>> outputList = new HashMap<>();
    // for (String crypto : cryptos) {
    // HashMap<String, CoinsCurrency> outputMap = new HashMap<>();
    // CoinsCurrency coinsCurrency = resultMap.getOrDefault(crypto, new
    // CoinsCurrency());
    // outputMap.put(crypto, coinsCurrency);
    // outputList.put(crypto, outputMap);
    // }

    return exchangeRates;
  }
}