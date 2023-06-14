package com.codewave.project.crypto.channel.service;

import com.codewave.project.crypto.channel.dto.response.ChannelDto;
import com.codewave.project.crypto.channel.infra.enums.Currency;

public interface ExchangeService {

  ChannelDto exchange(Currency curr1, Currency curr2);

}
