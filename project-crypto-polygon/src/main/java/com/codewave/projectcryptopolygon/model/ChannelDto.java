package com.codewave.projectcryptopolygon.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Exchanger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChannelDto {

  List<ExchangeRate> exchangeRates;

  public ExchangeRate buildExahgeRate() {
    return new ExchangeRate();
  }

  @Data
  public class ExchangeRate {
    private String fromCurr;
    private String toCurr;
    private BigDecimal rate;
  }
}
