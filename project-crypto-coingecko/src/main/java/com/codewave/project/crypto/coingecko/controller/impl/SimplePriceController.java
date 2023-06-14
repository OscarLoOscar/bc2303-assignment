package com.codewave.project.crypto.coingecko.controller.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewave.project.crypto.coingecko.controller.SimplePriceOperation;
import com.codewave.project.crypto.coingecko.infra.enums.Currency;
import com.codewave.project.crypto.coingecko.mapper.ChannelDtoMapper;
import com.codewave.project.crypto.coingecko.model.dto.ChannelDto;
import com.codewave.project.crypto.coingecko.service.SimplePriceService;

@RestController
@RequestMapping(value = "/crypto/coingecko/v1")
public class SimplePriceController implements SimplePriceOperation {

  @Autowired
  SimplePriceService simplePriceService;

  @Override
  public ChannelDto getExchangeRates(List<Currency> coins, List<Currency> currencies) {
    return ChannelDtoMapper.map(simplePriceService.getSimplePriceWithRedis(coins, currencies));
  }

  @Override
  public ChannelDto getExchangeRate(Map<String, String> currencyMap) {
    //validation -> throw IAE
    
    System.out.println(currencyMap.toString());
    List<Currency> fromCurrs = currencyMap.entrySet().stream()
        .filter(e -> "fromCurr".equals(e.getKey()))
        .map(e -> Currency.valueOf(e.getValue()))
        .collect(Collectors.toList());
    List<Currency> toCurrs = currencyMap.entrySet().stream()
        .filter(e -> "toCurr".equals(e.getKey()))
        .map(e -> Currency.valueOf(e.getValue()))
        .collect(Collectors.toList());
    return ChannelDtoMapper
        .map(simplePriceService.getSimplePriceWithRedis(fromCurrs, toCurrs));
  }

}