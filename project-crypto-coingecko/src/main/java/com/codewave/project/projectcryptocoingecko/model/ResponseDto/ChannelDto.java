package com.codewave.project.projectcryptocoingecko.model.ResponseDto;

//https://api.coingecko.com/api/v3/simple/price?ids=Bitcoin%2CEthereum%2CTether%2CDogecoin&vs_currencies=usd%2Chkd
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

  List<ExchangeRate> exchangeRates;

  public ExchangeRate buildExahgeRate() {
    return new ExchangeRate();
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
