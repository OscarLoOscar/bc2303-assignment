package com.codewave.project.projectcryptochannel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.codewave.project.projectcryptochannel.model.Channels;
import com.codewave.project.projectcryptochannel.model.dto.ChannelsDto;

//ResponseEntity -> 連database用
public interface ChannelOperation {

  @GetMapping(value = "/allChannel")
  public ResponseEntity<Channels> getAllChannels();

  @PostMapping(value = "/channel/{id}")
  public ResponseEntity<ChannelsDto> createNewChannelById(@RequestBody Channels channels,
      @PathVariable(value = "id") Long id);
}
