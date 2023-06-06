package com.codewave.project.projectcryptocoingecko.controller.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewave.project.projectcryptocoingecko.controller.CoingeckoOperations;
import com.codewave.project.projectcryptocoingecko.infra.enums.Currency;
import com.codewave.project.projectcryptocoingecko.infra.exception.BusinessException;
import com.codewave.project.projectcryptocoingecko.infra.response.CoinsApi;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.ChannelDto;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.CoinMarketRespDto;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.CoinsCurrency;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.ChannelDto.ExchangeRate;
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

  // @Override
  // public ChannelDto getExchangeRate(List<String> cryptos,
  // List<String> currencies) throws BusinessException {
  // return ChannelDto.builder()
  // .exchangeRates(CoinsApi.map(coinsService.getSimplePrices(cryptos,
  // currencies)))
  // .build();
  // }

  // @Override
  // public List<ExchangeRate> getExchangeRate(List<String> cryptos, List<String>
  // currencies) throws BusinessException {

  // HashMap<String, CoinsCurrency> result =
  // coinsService.getSimplePricesByMap(cryptos, currencies);
  // log.info("contro test 1 = " + result.values());
  // //[CoinsCurrency(usd=null, hkd=null), CoinsCurrency(usd=null, hkd=null),
  // CoinsCurrency(usd=null, hkd=null), CoinsCurrency(usd=null, hkd=null)]
  // log.info("contro test 1 = " + result.get(cryptos));
  // log.info("contro test 1 = " + result.get(currencies));
  // Map<String, Map<String, BigDecimal>> mapResult = new HashMap<>();

  // for (Map.Entry<String, CoinsCurrency> entry : result.entrySet()) {
  // Map<String, BigDecimal> prices = new HashMap<>();
  // prices.put("usd", entry.getValue().getUsd());
  // prices.put("hkd", entry.getValue().getHkd());
  // mapResult.put(entry.getKey(), prices);
  // }

  // List<ExchangeRate> exchangeRates = new ArrayList<>();
  // ChannelDto channelDto = new ChannelDto();

  // for (String crypto : cryptos) {
  // Map<String, BigDecimal> prices = mapResult.get(crypto);
  // if (prices == null) {
  // continue;
  // }
  // for (String curriency : currencies) {
  // BigDecimal rate = prices.get(curriency);
  // ExchangeRate exchangeRate = channelDto.buildExahgeRate();
  // exchangeRate.setFromCurr(crypto);
  // exchangeRate.setToCurr(curriency);
  // exchangeRate.setRate(rate);
  // exchangeRates.add(exchangeRate);
  // }
  // }
  // return exchangeRates;
  // }

  @Override
  public List<Map<String, Object>> getExchangeRate(List<String> cryptos, List<String> currencies)
      throws BusinessException {
    log.info("crypto" + cryptos);
    log.info("currency" + currencies);
    HashMap<String, List<String>> exchangeRates = coinsService.getExchangeRates(cryptos, currencies);
    log.info("exchangeRates" + exchangeRates);
    // Create a map from cryptocurrency name to CoinsCurrency object
    Map<String, CoinsCurrency> resultMap = new HashMap<>();

    // for (ExchangeRate exchangeRate : exchangeRates) {
    //   String crypto = exchangeRate.getFromCurr();
    //   String currency = exchangeRate.getToCurr();
    //   log.info("crypto" + crypto);
    //   log.info("currency" + currency);
    //   CoinsCurrency coinsCurrency = resultMap.getOrDefault(crypto, new CoinsCurrency());
    //   if ("usd".equals(currency)) {
    //     coinsCurrency.setUsd(exchangeRate.getRate());
    //   } else if ("hkd".equals(currency)) {
    //     coinsCurrency.setHkd(exchangeRate.getRate());
    //   }
    //   resultMap.put(crypto, coinsCurrency);
    // }
    // Convert the map to the desired output format
    List<Map<String, Object>> outputList = new ArrayList<>();
    for (String crypto : cryptos) {
      Map<String, Object> outputMap = new HashMap<>();
      CoinsCurrency coinsCurrency = resultMap.getOrDefault(crypto, new CoinsCurrency());
      outputMap.put(crypto, coinsCurrency);
      outputList.add(outputMap);
    }

    return outputList;
  }

  // @Override
  // public List<String> stringToList(List<String> coins) {
  // return coins;
  // }
}