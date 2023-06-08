package com.codewave.project.projectcryptochannel.controller.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewave.project.projectcryptochannel.controller.ChannelOperation;
import com.codewave.project.projectcryptochannel.model.Channels;
import com.codewave.project.projectcryptochannel.model.dto.ChannelsDto;
import com.codewave.project.projectcryptochannel.service.ChannelService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/crypto/admin/api/v1")
public class ChannelController implements ChannelOperation {
  @Autowired
  ModelMapper modelMapper;

  @Autowired
  ChannelService channelService;

  @Override
  public ResponseEntity<ChannelsDto> createNewChannelById(Channels channels, Long id) {
    Channels result = channelService.addChannelById(channels, id);
    log.info("Controller : " + result);
    ResponseEntity<ChannelsDto> response = null;
    if (result != null) {
      ChannelsDto channelsDto = modelMapper.map(result, ChannelsDto.class);
      response = ResponseEntity.ok(channelsDto);
    } else {
      response = ResponseEntity.notFound().build();
    }
    return response;

  }

  @Override
  public ResponseEntity<Channels> getAllChannels() {
    return channelService.getAllChannels();
  }

}
