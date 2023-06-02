package com.codewave.project.projectcryptocoingecko.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coin {

  private String id;
  private String symbol;
  private String name;
  private String image;
  private BigDecimal currentPrice;
  private BigDecimal marketCap;
  private BigDecimal marketCapRank;
  private BigDecimal fullyDilutedValuation;
  private BigDecimal totalVolume;
  private BigDecimal high24h;
  private BigDecimal low24h;
  private BigDecimal priceChange24h;
  private BigDecimal priceChangePercentag24h;
  private BigDecimal marketCapChange24h;
  private BigDecimal marketCapChangePercentage_24h;
  private BigDecimal circulatingSupply;
  private BigDecimal totalSupply;
  private BigDecimal maxSupply;
  private BigDecimal ath;
  private BigDecimal athChangePercentage;
  private LocalDateTime athDate;
  private BigDecimal atl;
  private BigDecimal atlChangePercentage;
  private LocalDateTime atlDate;
  private Roi roi;
  private LocalDateTime lastUpdated;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public class Roi {
    private BigDecimal times;
    private String currency;
    private BigDecimal percentage;
  }
}
