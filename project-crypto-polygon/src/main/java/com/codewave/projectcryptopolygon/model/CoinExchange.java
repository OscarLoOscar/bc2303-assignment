package com.codewave.projectcryptopolygon.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.codec.ServerCodecConfigurer;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CoinExchange implements Serializable {

  BigDecimal coint;
  String status;
  String ticket;
  BigDecimal queryCount;
  BigDecimal resultCount;
  Boolean adjusted;
  @JsonProperty(value = "results")
  List<ExchangeResult> exchangeResults;

  public String getCryptoString() {
    return this.ticket.substring(2, 5); // X:BTCUSD-> BTC
  }

  public String getCurrencyString() {
    return this.ticket.substring(5, 8); // X:BTCUSD - > USD
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class ExchangeResult implements Serializable {
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
    BigDecimal numberOfTransactions;

  }
}
