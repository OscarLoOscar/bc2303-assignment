package com.codewave.project.crypto.coingecko.controller;

import java.util.List;
import java.util.Map;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.codewave.project.crypto.coingecko.annotation.ValidSimplePrice;
import com.codewave.project.crypto.coingecko.infra.enums.Currency;
import com.codewave.project.crypto.coingecko.model.dto.ChannelDto;

@Validated
public interface SimplePriceOperation {

        /**
         * /simple-price?coins=BTC,ETH,USDT,XRP,DOGE&currency=USD,HKD.
         * 
         * @param coins
         * @param currencies
         * @return ChannelDto
         */
        @GetMapping(value = "/exchanges")
        ChannelDto getExchangeRates(@RequestParam(value = "coins") List<Currency> coins,
                        @RequestParam(value = "currencies") List<Currency> currencies);

        /**
         * /simple-price?coins=BTC&currency=USD.
         * 
         * @param coins
         * @param currencies
         * @return
         */
        @GetMapping(value = "/exchange/{fromCurr}/{toCurr}")
        ChannelDto getExchangeRate(
                        @ValidSimplePrice(customMessage = "Invalid Currency Path Variables!") //
                        @PathVariable Map<String, String> currencyMap);

}
