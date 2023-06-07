package com.codewave.project.projectcryptochannel.controller.impl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codewave.project.projectcryptochannel.model.Channels;

public interface ChannelOperation {
  @PostMapping(value = "/channel")
  public Channels createNewChannel(@RequestParam("tranType") String exRate,
      @RequestParam("sourceApp") String cryptoWeb);

}
