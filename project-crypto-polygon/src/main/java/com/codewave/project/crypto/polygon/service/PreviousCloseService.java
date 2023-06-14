package com.codewave.project.crypto.polygon.service;

import com.codewave.project.crypto.polygon.infra.enums.Currency;
import com.codewave.project.crypto.polygon.model.PreviousClose;

public interface PreviousCloseService {

  PreviousClose getPreviousCloseWithRedis(Currency fromCurr, Currency toCurr);

}
