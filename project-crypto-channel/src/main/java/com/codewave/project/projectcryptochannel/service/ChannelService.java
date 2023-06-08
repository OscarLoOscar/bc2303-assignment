package com.codewave.project.projectcryptochannel.service;

import org.springframework.http.ResponseEntity;

import com.codewave.project.projectcryptochannel.model.Channels;

public interface ChannelService {
  Channels addChannelById(Channels newChannels, Long id);

  ResponseEntity<Channels> getAllChannels();
}
