package com.codewave.project.crypto.polygon.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChannelDto {

  private List<ExchangeRate> exchangeRates;

  public static ChannelDto.ExchangeRate emptyExchangeRate() {
    return new ChannelDto().new ExchangeRate();
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public class ExchangeRate {
    private String fromCurr;
    private String toCurr;
    private BigDecimal rate;
  }

}
