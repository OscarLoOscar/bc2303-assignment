package com.codewave.project.crypto.coingecko.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.codewave.project.crypto.coingecko.infra.enums.Currency;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CoinsMappingConfig implements CommandLineRunner {

  // Read Only, No Concurrent issue
  private static final Map<Currency, String> coins = new HashMap<>(); // EnumMap<Enum, non-Enum>

  private static void init() {
    coins.put(Currency.BTC, "bitcoin");
    coins.put(Currency.ETH, "ethereum");
    coins.put(Currency.USDT, "tether");
    coins.put(Currency.DOGE, "dogecoin");
    coins.put(Currency.XRP, "ripple");
    coins.put(Currency.HKD, "hkd");
    coins.put(Currency.USD, "usd");
  }

  public static String get(Currency key) {
    return coins.get(key);
  }

  @Override
  public void run(String... args) {
    // Initialize the coin mapping (Temp Solution, to be enhanced by DB)
    init();
    // scheduletask -> load from DB
    // refresh the Map

    // Prove the run() method is being called before app start completed
    log.info("EXECUTING : command line runner");
    for (int i = 0; i < args.length; ++i) {
      log.info("args[{}]: {}", i, args[i]);
    }
  }

}
