package com.codewave.project.crypto.coingecko.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.codewave.project.crypto.coingecko.config.CustomLocalDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) // Convention Over Configuration
public class CoinMarketResp {
  // 26 fields, Java Naming Conversion: CamlCase
  private String id;
  private String symbol;
  private String name;
  private String image;
  private BigDecimal currentPrice; // CamlCase
  private Long marketCap;
  private Integer marketCapRank;
  private Long fullyDilutedValuation;
  private Long totalVolume;
  @JsonProperty("high_24h")
  private BigDecimal high24h;
  @JsonProperty("low_24h")
  private BigDecimal low24h;
  @JsonProperty("price_change_24h")
  private BigDecimal priceChange24h;
  @JsonProperty("price_change_percentage_24h")
  private BigDecimal priceChangePercentage24h;
  @JsonProperty("market_cap_change_24h")
  private BigDecimal marketCapChange24h;
  @JsonProperty("market_cap_change_percentage_24h")
  private BigDecimal marketCapChangePercentage24h;
  private BigDecimal circulatingSupply;
  private BigDecimal totalSupply;
  private BigDecimal maxSupply;
  private BigDecimal ath;
  private BigDecimal athChangePercentage;
  @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
  private LocalDateTime athDate;
  private BigDecimal atl;
  private BigDecimal atlChangePercentage;
  @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
  private LocalDateTime atlDate;
  private Roi roi;
  @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
  private LocalDateTime lastUpdated;

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor // important, error without no arguement
  public static class Roi {
    private BigDecimal times;
    private String currency;
    private BigDecimal percentage;
  }

  public CoinMarketResp(String id, String symbol, String name, String image,
      double currentPrice, long marketCap, int marketCapRank, long fullyDilutedValuation,
      double high24h, long totalVolume, double low24h, double priceChange24h, double priceChangePercentage24h,
      double marketCapChange24h, double marketCapChangePercentage24h, double circulatingSupply, double totalSupply,
      double maxSupply, double ath, double athChangePercentage, LocalDateTime athDate,
      double atl, double atlChangePercentage, LocalDateTime atlDate, Roi roi, LocalDateTime lastUpdated) {
    this.id = id;
    this.symbol = symbol;
    this.name = name;
    this.image = image;
    this.currentPrice = BigDecimal.valueOf(currentPrice);
    this.marketCap = marketCap;
    this.marketCapRank = marketCapRank;
    this.fullyDilutedValuation = fullyDilutedValuation;
    this.totalVolume = totalVolume;
    this.high24h = BigDecimal.valueOf(high24h);
    this.low24h = BigDecimal.valueOf(low24h);
    this.priceChange24h = BigDecimal.valueOf(priceChange24h);
    this.priceChangePercentage24h = BigDecimal.valueOf(priceChangePercentage24h);
    this.marketCapChange24h = BigDecimal.valueOf(marketCapChange24h);
    this.marketCapChangePercentage24h = BigDecimal.valueOf(marketCapChangePercentage24h);
    this.circulatingSupply = BigDecimal.valueOf(circulatingSupply);
    this.totalSupply = BigDecimal.valueOf(totalSupply);
    this.maxSupply = BigDecimal.valueOf(maxSupply);
    this.ath = BigDecimal.valueOf(ath);
    this.athChangePercentage = BigDecimal.valueOf(athChangePercentage);
    this.athDate = athDate;
    this.atl = BigDecimal.valueOf(atl);
    this.atlChangePercentage = BigDecimal.valueOf(atlChangePercentage);
    this.atlDate = atlDate;
    this.roi = roi;
    this.lastUpdated = lastUpdated;
  }

}
