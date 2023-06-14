package com.codewave.project.crypto.polygon.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;

import com.codewave.project.crypto.polygon.infra.enums.Currency;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CoinsMappingConfig implements CommandLineRunner {

  // Read Only, No Concurrent issue
  private static final Map<Currency, String> coins = new HashMap<>();

  private static void init() {
    coins.put(Currency.BTC, "BTC");
    coins.put(Currency.ETH, "ETH");
    coins.put(Currency.USDT, "USDT");
    coins.put(Currency.DOGE, "DOGE");
    coins.put(Currency.XRP, "XRP");
  }

  public static String get(Currency key) {
    return coins.get(key);
  }

  @Override
  public void run(String... args) {
    // Prove the run() method is being called before app start completed
    log.info("EXECUTING : command line runner");
    for (int i = 0; i < args.length; ++i) {
      log.info("args[{}]: {}", i, args[i]);
    }
    // Initialize the coin mapping (Temp Solution, to be enhanced by DB)
    init();
  }

}
