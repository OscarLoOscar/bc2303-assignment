package com.codewave.project.crypto.coingecko.controller.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewave.project.crypto.coingecko.controller.CoinMarketOperation;
import com.codewave.project.crypto.coingecko.model.dto.CoinMarketRespDto;
import com.codewave.project.crypto.coingecko.service.CoinMarketService;

@RestController
@RequestMapping(value = "/crypto/coingecko/v1")
public class CoinMarketController implements CoinMarketOperation {

  @Autowired
  CoinMarketService coinMarketService;

  @Autowired
  ModelMapper mapper;

  @Override
  @CrossOrigin
  public List<CoinMarketRespDto> getCoinMarket() {
    return coinMarketService.getCoinMarketWithRedis().stream()
        .map(coin -> mapper.map(coin, CoinMarketRespDto.class))
        .collect(Collectors.toList());
  }

}