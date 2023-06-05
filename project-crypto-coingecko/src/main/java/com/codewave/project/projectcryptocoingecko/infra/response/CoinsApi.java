package com.codewave.project.projectcryptocoingecko.infra.response;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.codewave.project.projectcryptocoingecko.config.ExchangeRateUrlConfig;
import com.codewave.project.projectcryptocoingecko.infra.exception.BusinessException;
import com.codewave.project.projectcryptocoingecko.infra.util.UriScheme;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.ChannelDto;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.CoinCurrencyMap;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.CoinsCurrency;
import com.codewave.project.projectcryptocoingecko.model.ResponseDto.ChannelDto.ExchangeRate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CoinsApi {

  @Autowired
  RestTemplate restTemplate;

  public static List<ChannelDto.ExchangeRate> map(
      Map<String, CoinsCurrency> coinsCurrencyMap) {

    List<ChannelDto.ExchangeRate> exchangeRates = new ArrayList<>();

    for (Map.Entry<String, CoinsCurrency> entry : coinsCurrencyMap.entrySet()) {
      // crypto to usd
      ExchangeRate exchangeRate = new ChannelDto().new ExchangeRate();
      exchangeRate.setFromCurr(entry.getKey());
      exchangeRate.setToCurr("usd");
      exchangeRate.setRate(entry.getValue().getUsd());
      exchangeRates.add(exchangeRate);
      // cryoto to hkd
      exchangeRate = new ChannelDto().new ExchangeRate();
      exchangeRate.setFromCurr(entry.getKey());
      exchangeRate.setToCurr("hkd");
      exchangeRate.setRate(entry.getValue().getHkd());
      exchangeRates.add(exchangeRate);
      // usd to crypto
      exchangeRate.setFromCurr("usd");
      exchangeRate.setToCurr(entry.getKey());
      exchangeRate.setRate(BigDecimal.ONE.divide(entry.getValue().getUsd(),
          new MathContext(6, RoundingMode.HALF_UP)));
      // BigDecimal.ONE.add(BigDecimal.TEN)表示将1和10相加
      // MathContext(int setPrecision, RoundingMode setRoundingMode)
      // hkd to crypto
      exchangeRate.setFromCurr("hkd");
      exchangeRate.setToCurr(entry.getKey());
      exchangeRate.setRate(BigDecimal.ONE.divide(entry.getValue().getHkd(),
          new MathContext(6, RoundingMode.HALF_UP)));

    }
    return exchangeRates;
  }

  public <T> T invoke(String domain, String endpoint2,
      HashMap<String, String> queryParms,
      Class<T> returnType) throws BusinessException { // CoinsCurrencyMap.class
    try {
      String http = "https://api.coingecko.com/api/v3/simple/price";
      UriComponentsBuilder url = UriComponentsBuilder.fromUriString(http)
          .pathSegment(",", domain)
          .pathSegment(",", endpoint2);
      log.info("A " + domain);
      log.info("B " + endpoint2);
      // .path(version) // api/v3
      // .path(pathSegment); // simple/price
      // construct params to url
      // concat
      // "?vs_currency=usd,hkd&ids=bitcoin,tether,..."
      for (Map.Entry<String, String> entry : queryParms.entrySet()) {
        url = url.queryParam(entry.getKey(), entry.getValue());
      }
      // https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&per_page=100&page=1&order=market_cap_desc
      String urlString = url.build().toString();
      // invoke coingecko api with pre-defined return type (CoinsMarkats)
      return restTemplate.getForObject(urlString,
          returnType);
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException(80001, "Call coinGecko service fail.");
    }
  }
}