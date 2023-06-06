package com.codewave.project.projectcryptocoingecko.model.ResponseDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoinsCurrency implements Serializable {
  private BigDecimal usd ;
  private BigDecimal hkd;
}
