package com.codewave.project.crypto.coingecko.mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.codewave.project.crypto.coingecko.model.SimplePriceResp;
import com.codewave.project.crypto.coingecko.model.dto.ChannelDto;
import com.codewave.project.crypto.coingecko.model.dto.ChannelDto.ExchangeRate;

public final class ChannelDtoMapper {

  public static ChannelDto map(SimplePriceResp simplePriceResp) {
    List<ExchangeRate> exchangeRates = new ArrayList<>();
    for (String key : simplePriceResp.keySet()) {
      ExchangeRate exchangeRate = ChannelDto.emptyExchangeRate();
      for (Map.Entry<String, BigDecimal> rates : simplePriceResp.get(key).entrySet()) {
        exchangeRate.setFromCurr(key); // "bitcoin"
        exchangeRate.setToCurr(rates.getKey()); // "hkd"
        exchangeRate.setRate(rates.getValue()); // 111.11
      }
      exchangeRates.add(exchangeRate);

      ExchangeRate exchangeRateReversed = ChannelDto.emptyExchangeRate();
      for (Map.Entry<String, BigDecimal> rates : simplePriceResp.get(key).entrySet()) {
        exchangeRateReversed.setFromCurr(rates.getKey());
        exchangeRateReversed.setToCurr(key);
        exchangeRateReversed.setRate(BigDecimal.ONE.divide(rates.getValue(), 10, RoundingMode.HALF_UP));
      }
      exchangeRates.add(exchangeRateReversed);
    }
    return ChannelDto.builder()
        .exchangeRates(exchangeRates)
        .build();
  }

}
