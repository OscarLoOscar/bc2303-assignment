package com.codewave.projectcryptochannel.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codewave.projectcryptochannel.infra.exception.BusinessException;
import com.codewave.projectcryptochannel.model.dto.ChannelDto;

public interface ChannelOperation {

  @GetMapping(value = "/coingekco/ex-rate")
  ChannelDto getCoingekco(@RequestParam("coins") List<String> Cryptos,
      @RequestParam("currency") List<String> currencies) throws BusinessException;

  @GetMapping(value = "/polygon/ex-rate")
  ChannelDto getPolygon(@RequestParam("coins") List<String> Cryptos, @RequestParam("currency") List<String> currencies)
      throws BusinessException;

}
