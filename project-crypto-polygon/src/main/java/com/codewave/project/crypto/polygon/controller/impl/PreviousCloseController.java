package com.codewave.project.crypto.polygon.controller.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewave.project.crypto.polygon.controller.PreviousCloseOperation;
import com.codewave.project.crypto.polygon.dto.ChannelDto;
import com.codewave.project.crypto.polygon.infra.enums.Currency;
import com.codewave.project.crypto.polygon.mapper.ChannelDtoMapper;
import com.codewave.project.crypto.polygon.model.PreviousClose;
import com.codewave.project.crypto.polygon.service.PreviousCloseService;

@RestController
@RequestMapping(value = "/crypto/polygon/v1")
public class PreviousCloseController implements PreviousCloseOperation {

  @Autowired
  PreviousCloseService previousCloseService;

  @Override
  public ChannelDto getExchanges(Map<String, String> currentMap) {
    Currency fromCurr = currentMap.entrySet().stream()
        .filter(e -> "fromCurr".equals(e.getKey()))
        .map(e -> Currency.valueOf(e.getValue()))
        .findFirst().orElse(null);
    Currency toCurr = currentMap.entrySet().stream()
        .filter(e -> "toCurr".equals(e.getKey()))
        .map(e -> Currency.valueOf(e.getValue()))
        .findFirst().orElse(null);
    PreviousClose previousClose = previousCloseService.getPreviousCloseWithRedis(fromCurr, toCurr);
    return ChannelDtoMapper.map(previousClose, currentMap);
  }

}
