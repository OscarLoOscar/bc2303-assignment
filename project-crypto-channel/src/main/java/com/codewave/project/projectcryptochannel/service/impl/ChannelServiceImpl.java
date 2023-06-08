package com.codewave.project.projectcryptochannel.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.codewave.project.projectcryptochannel.model.Channels;
import com.codewave.project.projectcryptochannel.repository.channelRepository;
import com.codewave.project.projectcryptochannel.service.ChannelService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChannelServiceImpl implements ChannelService {
  @Autowired
  channelRepository channelRepository;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public Channels addChannelById(Channels newChannels, Long id) {
    log.info("Service 1 : " + newChannels.toString());
    log.info("Service 2 : " + id.toString());
    if (id == null || newChannels == null) {
      return null;
    }
    return channelRepository.findById(id)
        .map(channles -> {
          channles.setChannelCode(newChannels.getChannelCode());
          channles.setChannelUrl(newChannels.getChannelUrl());
          return channelRepository.save(channles);
        }).orElseGet(() -> {
          return null;
        });
  }

  @Override
  public ResponseEntity<Channels> getAllChannels() {
    List<Channels> response = channelRepository.findAll();
    Channels result = modelMapper.map(response, Channels.class);
    return ResponseEntity.ok().body(result);

  }
}
