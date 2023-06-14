package com.codewave.project.crypto.polygon.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PreviousClose {
  private String ticker;
  private Integer queryCount;
  private Integer resultsCount;
  private Boolean adjusted;
  private List<Result> results;
  private String status;
  @JsonProperty("request_id")
  private String requestId;
  private Integer count;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Result {
    @JsonProperty("T")
    private String ticker;
    @JsonProperty("v")
    private BigDecimal volume;
    @JsonProperty("vw")
    private BigDecimal weightedAvgPrice;
    @JsonProperty("o")
    private BigDecimal openPrice;
    @JsonProperty("c")
    private BigDecimal closePrice;
    @JsonProperty("h")
    private BigDecimal highestPrice;
    @JsonProperty("l")
    private BigDecimal lowestPrice;
    @JsonProperty("t")
    private Long unixTime;
    @JsonProperty("n")
    private Integer numOfTrans;
  }
  
}
