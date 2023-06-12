package com.codewave.projectcryptochannel.controller.impl;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewave.projectcryptochannel.controller.ChannelOperation;
import com.codewave.projectcryptochannel.infra.exception.BusinessException;
import com.codewave.projectcryptochannel.model.dto.ChannelDto;

@RestController
@RequestMapping(value = "/crypto/api/v1/channel")
public class ChannelController implements ChannelOperation {



  @Override
  public ChannelDto getCoingekco(List<String> Cryptos, List<String> currencies) throws BusinessException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getCoingekco'");
  }

  @Override
  public ChannelDto getPolygon(List<String> Cryptos, List<String> currencies) throws BusinessException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getPolygon'");
  }
  
}
