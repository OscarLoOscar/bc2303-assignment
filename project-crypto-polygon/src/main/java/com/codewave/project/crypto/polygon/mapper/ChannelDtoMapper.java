package com.codewave.project.crypto.polygon.mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.codewave.project.crypto.polygon.dto.ChannelDto;
import com.codewave.project.crypto.polygon.dto.ChannelDto.ExchangeRate;
import com.codewave.project.crypto.polygon.model.PreviousClose;

public final class ChannelDtoMapper {

  public static ChannelDto map(PreviousClose previousClose, Map<String, String> currencyMap) {
    BigDecimal rate = null;
    String toCurr = null;
    String fromCurr = null;
    if (currencyMap.get("fromCurr").equals("HKD") || currencyMap.get("toCurr").equals("HKD")) {
      rate = new BigDecimal(7.8).multiply(previousClose.getResults().get(0).getClosePrice());
      if (currencyMap.get("fromCurr").equals("HKD")) {
        fromCurr = "HKD";
        toCurr = previousClose.getTicker().substring(previousClose.getTicker().length() - 3); // from 2 to end
      }
      if (currencyMap.get("toCurr").equals("HKD")) {
        fromCurr = previousClose.getTicker().substring(2, previousClose.getTicker().length() - 3);
        toCurr = "HKD";
      }
    } else { // USD
      rate = previousClose.getResults().get(0).getClosePrice();
      toCurr = previousClose.getTicker().substring(previousClose.getTicker().length() - 3); // from 2 to end
      fromCurr = previousClose.getTicker().substring(2, previousClose.getTicker().length() - 3);
    }

    // Construct ExchangeRate and Reversed rate

    ExchangeRate exchangeRate = ChannelDto.emptyExchangeRate();
    exchangeRate.setFromCurr(fromCurr);
    exchangeRate.setToCurr(toCurr);
    exchangeRate.setRate(rate.setScale(10, RoundingMode.HALF_UP));
    ExchangeRate exchangeRateReversed = ChannelDto.emptyExchangeRate();
    exchangeRateReversed.setFromCurr(toCurr);
    exchangeRateReversed.setToCurr(fromCurr);
    exchangeRateReversed
        .setRate(BigDecimal.ONE.divide(rate, 10, RoundingMode.HALF_UP));
    // Construct List<ExchangeRate>
    List<ExchangeRate> exchangeRates = new ArrayList<>();
    exchangeRates.add(exchangeRate);
    exchangeRates.add(exchangeRateReversed);
    // Construct ChannelDto
    return ChannelDto.builder()
        .exchangeRates(exchangeRates)
        .build();
  }

}