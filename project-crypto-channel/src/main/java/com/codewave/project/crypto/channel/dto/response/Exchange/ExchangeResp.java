package com.codewave.project.crypto.channel.dto.response.Exchange;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import com.codewave.project.crypto.channel.infra.enums.Currency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExchangeResp implements Serializable{
  private Currency fromCurr;
  private BigDecimal fromCurrQuantity;
  private Currency toCurr;
  private BigDecimal toCurrQuantity;
  private UUID quoteId;

}
