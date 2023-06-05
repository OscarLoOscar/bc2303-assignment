package com.codewave.projectcryptopolygon.infra.response;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.codewave.projectcryptopolygon.infra.exception.BusinessException;
import com.codewave.projectcryptopolygon.model.ChannelDto;
import com.codewave.projectcryptopolygon.model.CoinExchange;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CoinsApi {

  @Autowired
  RestTemplate restTemplate;

  public static List<ChannelDto.ExchangeRate> map(List<CoinExchange> coinExchanges) {
    List<ChannelDto.ExchangeRate> exchangeRates = new ArrayList<>();

    for (CoinExchange coinExchange : coinExchanges) {
      // Set the rate for "crypto to Curency"
      ChannelDto.ExchangeRate exchangeRate = new ChannelDto().buildExahgeRate();
      exchangeRate.setFromCurr(coinExchange.getCryptoString());// BTC
      exchangeRate.setToCurr(coinExchange.getCurrencyString());// USD
      exchangeRate.setRate(coinExchange.getExchangeResults().get(0).getHighestPrice());
      exchangeRates.add(exchangeRate);
      // set the rate for currency to crypto
      exchangeRate = new ChannelDto().buildExahgeRate();
      exchangeRate.setFromCurr(coinExchange.getCurrencyString());
      exchangeRate.setToCurr(coinExchange.getCryptoString());
      exchangeRate.setRate(BigDecimal.ONE.divide(coinExchange.getExchangeResults().get(0).getHighestPrice(),
          new MathContext(6, RoundingMode.HALF_UP)));
      exchangeRates.add(exchangeRate);
    }
    return exchangeRates;
  }

  public <T> T invoke(String domain,
      String endpoint2,
      String pathSegment, HashMap<String, String> queryParms,
      Class<T> returnType) throws BusinessException { // CoinsCurrencyMap.class
    try {
      // url = https://api.coingecko.com/api/v3/coins/markets
      UriComponentsBuilder url = UriComponentsBuilder.fromUriString(domain)
          .pathSegment(endpoint2) // api/v3
          .path(pathSegment); // simple/price

      // construct params to url
      // concat
      // "?vs_currency=usd,hkd&ids=bitcoin,tether,..."
      for (Map.Entry<String, String> entry : queryParms.entrySet()) {
        url = url.queryParam(entry.getKey(), entry.getValue());
      }
      // https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&per_page=100&page=1&order=market_cap_desc
      String urlString = url.build().toString();

      log.info("url={}", urlString);

      // invoke coingecko api with pre-defined return type (CoinsMarkats)
      return restTemplate.getForObject(urlString, returnType);
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException(80001, "Call coinGecko service fail.");
    }
  }

}