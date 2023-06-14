package com.codewave.project.crypto.polygon.controller;

import java.util.Map;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.codewave.project.crypto.polygon.annotation.ValidPreviousClose;
import com.codewave.project.crypto.polygon.dto.ChannelDto;

@Validated
public interface PreviousCloseOperation {

  @GetMapping(value = "/exchange/{fromCurr}/{toCurr}")
  ChannelDto getExchanges(@ValidPreviousClose(customMessage = "Invalid Currency Path Variables!") //
  @PathVariable Map<String, String> currencyMap);

}
