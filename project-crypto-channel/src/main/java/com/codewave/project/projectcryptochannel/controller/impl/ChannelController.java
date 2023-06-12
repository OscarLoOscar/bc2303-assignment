package com.codewave.project.projectcryptochannel.controller.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewave.project.projectcryptochannel.controller.ChannelOperation;
import com.codewave.project.projectcryptochannel.model.ChannelTrans;
import com.codewave.project.projectcryptochannel.model.Channels;
import com.codewave.project.projectcryptochannel.repository.ChannelRepository;
import com.codewave.project.projectcryptochannel.repository.ChannelTransRepository;
import com.codewave.project.projectcryptochannel.service.ChannelService;

@RestController
@RequestMapping(value = "/crypto/admin/api/v1")
public class ChannelController implements ChannelOperation {

  @Autowired
  ChannelService channelService;

  @Autowired
  ChannelRepository channelRepository;

  @Autowired
  ChannelTransRepository channelTransRepository;

  @Autowired
  ModelMapper modelMapper;

  @Override
  public Channels getChannel(Long id) {
    // List<Channels> change = channelService.getAll();
    // Channels result = modelMapper.map(change, Channels.class);
    // return result;

    // Optional<Channels> optionalChannel = channelService.get(id);
    // if (optionalChannel.isPresent()) {
    // Channels result = modelMapper.map(optionalChannel.get(), Channels.class);
    // return result;
    // }
    // return null;
    return channelRepository.findById(id).orElseGet(null);
  }

  @Override
  public Channels createChannel(Channels channel) {
    return channelRepository.save(channel);
  }

  @Override
  public ChannelTrans createTransaction(Long channelId, ChannelTrans channelTrans) {
    Optional<Channels> optionalChannel = channelService.get(channelId);
    if (optionalChannel.isPresent()) {
      channelTrans.setChannel(optionalChannel.get());
      return channelService.save(channelTrans);
    }
    return null;
  }

  @Override
  public List<ChannelTrans> getTransaction(String source, String tranType) {
    return channelTransRepository.findBySourceAppAndTranType(source, tranType);
  }
}