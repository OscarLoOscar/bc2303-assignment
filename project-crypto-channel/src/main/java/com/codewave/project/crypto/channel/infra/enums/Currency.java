package com.codewave.project.crypto.channel.infra.enums;

import lombok.Getter;
import lombok.ToString;

/**
 * Includes all Traditional and Crypto Currency.
 * 
 * @author vincent.lau
 * @version
 * @apiNote
 */
@Getter
@ToString
public enum Currency {
  // Traditional Currency
  HKD('T'),
  USD('T'),
  // Crypto Currency
  BTC('C'),
  ETH('C'),
  USDT('C'),
  XRP('C'),
  DOGE('C');

  private char type;

  Currency(char type) {
    this.type = type;
  }

  public boolean isCrypto() {
    return this.type == 'C';
  }
}
