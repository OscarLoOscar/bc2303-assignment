package com.codewave.project.crypto.channel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.codewave.project.crypto.channel.dto.response.ChannelDto;
import com.codewave.project.crypto.channel.infra.enums.Currency;

public interface ExchangeOperation {

  @GetMapping(value = "/exchange/{curr1}/{curr2}")
  ChannelDto getExchange(@PathVariable Currency curr1, @PathVariable Currency curr2);

}
