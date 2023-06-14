package com.codewave.project.crypto.coingecko.service;

import java.util.List;

import com.codewave.project.crypto.coingecko.infra.enums.Currency;
import com.codewave.project.crypto.coingecko.model.SimplePriceResp;

public interface SimplePriceService {

  SimplePriceResp getSimplePriceWithRedis(List<Currency> fromCurr, List<Currency> toCurr);

}
