package com.codewave.project.projectcryptocoingecko.model.ResponseDto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CoinMarketRespDto {
  private String id;
  private String symbol;
  private String name;
  private String image;
  private BigDecimal totalVolume;
  private BigDecimal currentPrice;
  private BigDecimal marketCap;
  private BigDecimal marketCapRank;
  @JsonProperty("price_change_percentage_24h")
  private BigDecimal priceChangePercentage24h;

}
