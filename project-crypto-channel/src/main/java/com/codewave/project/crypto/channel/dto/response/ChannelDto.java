package com.codewave.project.crypto.channel.dto.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChannelDto {

  private List<ExchangeRate> exchangeRates;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class ExchangeRate {
    private String fromCurr;
    private String toCurr;
    private BigDecimal rate;
  }

}
