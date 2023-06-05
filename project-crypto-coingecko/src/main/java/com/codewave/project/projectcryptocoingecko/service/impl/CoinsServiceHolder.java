package com.codewave.project.projectcryptocoingecko.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.codewave.project.projectcryptocoingecko.infra.exception.BusinessException;
import com.codewave.project.projectcryptocoingecko.infra.response.CoinsApi;
import com.codewave.project.projectcryptocoingecko.model.CoinsMarketResp;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.ChannelDto;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.CoinCurrencyMap;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.CoinsCurrency;
import com.codewave.project.projectcryptocoingecko.service.CoinsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CoinsServiceHolder implements CoinsService {

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  RedisService redisService;

  @Autowired
  @Qualifier(value = "allCoinUrl")
  String coinUrl;

  @Autowired
  @Qualifier(value = "exchangeRateUrl")
  String exchangeRateUrl;

  private CoinsMarketResp[] getCoinMarket() {
    return restTemplate.getForObject(coinUrl, CoinsMarketResp[].class);
  }

  private ChannelDto.ExchangeRate getExchangeRate() {
    return restTemplate.getForObject(exchangeRateUrl, ChannelDto.ExchangeRate.class);
  }

  @Override
  public List<CoinsMarketResp> getAllData() {
    try {
      List<String> params = new ArrayList<>(Arrays.asList(null, "usd"));
      log.info("stpe1 : coinUrl : " + coinUrl);
      // Invoke Coingecko API
      CoinsMarketResp[] coins = restTemplate.getForObject(coinUrl, CoinsMarketResp[].class, params.toArray());
      log.info("step2 : 4 :" + coins);
      // Set the array result to redis
      redisService.setCoinMarketResp(coins); // Async
      return Arrays.asList(coins);
    } catch (RestClientException e) {
      // Requirement: return last data in redis if the restful call to coingecko fail
      return Arrays.asList(redisService.getCoinMarketResp()); // handled JsonProcessingException
    }
  }

  @Override
  public HashMap<String, CoinsCurrency> getSimplePrices(List<String> cryptos, List<String> currencies)
      throws BusinessException {

    // Alternative: String.join(",", cryptos);
    // Change the List<String> to String (bitcoin,tether)
    log.info("exchangeRateUrl = " + exchangeRateUrl);
    // incorrect path
    // https://api.coingecko.com/api/v3/simple?ids=Bitcon,Ethereum,Tether,Dogecoin&vs_currencies=usd,hkd
    // https://api.coingecko.com/api/v3/simple/price?ids=Bitcoin%2CEthereum%2CTether%2CDogecoin&vs_currencies=usd%2Chkd
    // https://api.coingecko.com/api/v3/simple?ids=Bitcon,Ethereum,Tether,Dogecoin&vs_currencies=usd,hkd
    log.info("1" + cryptos.toString());
    log.info("2" + currencies.toString());
    String cryptoStr = cryptos.stream().collect(Collectors.joining(","));
    String currencyStr = currencies.stream().collect(Collectors.joining(","));
    // Concat Url:
    // ?vs_currencies=usd,hkd&ids=bitcoin,ethereum,tether,dogecoin,ripple
    LinkedHashMap<String, String> hMap = new LinkedHashMap<>();
    hMap.put("ids", cryptoStr);
    hMap.put("vs_currencies", currencyStr);

    CoinsApi coinsApi = new CoinsApi();
    CoinCurrencyMap coinCurrencyMap = new CoinCurrencyMap();
    HashMap<String, CoinsCurrency> result = new HashMap<>();
    for (Map.Entry<String, HashMap<String, CoinsCurrency>> entry : coinCurrencyMap.entrySet()) {
      String crypto = entry.getKey();
      HashMap<String, CoinsCurrency> prices = entry.getValue();
      CoinsCurrency value = prices.get(currencyStr);
      result.put(crypto, value);

    }
    return result;
  }

}
