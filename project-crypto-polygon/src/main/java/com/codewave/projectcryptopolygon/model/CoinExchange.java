package com.codewave.projectcryptopolygon.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CoinExchange implements Serializable {
  String ticker;
  Integer queryCount;
  Integer resultsCount;
  Boolean adjusted;
  List<Result> Results;
  String status;
  @JsonProperty("request_id")
  String requestId;
  Integer count;

  public String getCryptoString() {
    return this.ticker.substring(2, 5); // X:BTCUSD-> BTC
  }

  public String getCurrencyString() {
    return this.ticker.substring(5, 8); // X:BTCUSD - > USD
  }

  @AllArgsConstructor
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Result {
    @JsonProperty(value = "T")
    String ticker;
    @JsonProperty(value = "v")
    BigDecimal tradingVolume;
    @JsonProperty(value = "vw")
    BigDecimal volumeWeightedAveragePrice;
    @JsonProperty(value = "o")
    BigDecimal operPrice;
    @JsonProperty(value = "c")
    BigDecimal closePrice;
    @JsonProperty(value = "h")
    BigDecimal highestPrice;
    @JsonProperty(value = "l")
    BigDecimal lowestPrice;
    @JsonProperty(value = "t")
    BigDecimal unixMsecTimestamp;
    @JsonProperty(value = "n")
    Integer numberOfTransactions;

  }
}
