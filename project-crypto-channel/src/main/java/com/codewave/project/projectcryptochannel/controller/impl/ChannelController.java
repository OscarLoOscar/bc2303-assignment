package com.codewave.project.projectcryptochannel.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewave.project.projectcryptochannel.model.Channels;

@RestController
@RequestMapping(value = "/crypto/admin/api/v1")
public class ChannelController implements ChannelOperation{

  @Override
  public Channels createNewChannel(String exRate, String cryptoWeb) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createNewChannel'");
  }
  
}
