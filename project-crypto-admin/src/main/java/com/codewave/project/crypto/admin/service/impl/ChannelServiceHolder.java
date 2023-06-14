package com.codewave.project.crypto.admin.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewave.project.crypto.admin.dto.request.ChannelReq;
import com.codewave.project.crypto.admin.dto.response.ChannelResp;
import com.codewave.project.crypto.admin.dto.response.MappingResp;
import com.codewave.project.crypto.admin.entity.ChannelEntity;
import com.codewave.project.crypto.admin.infra.enums.Source;
import com.codewave.project.crypto.admin.repository.ChannelRepository;
import com.codewave.project.crypto.admin.service.ChannelService;
import com.codewave.project.crypto.admin.service.MappingService;

@Service
public class ChannelServiceHolder implements ChannelService {

  @Autowired
  MappingService mappingService;

  @Autowired
  ChannelRepository channelRepository;

  @Autowired
  ModelMapper mapper;

  @Override
  public ChannelResp findChannel(Long id) {
    ChannelEntity channelEntity = channelRepository.findById(id).orElse(null);
    return mapper.map(channelEntity, ChannelResp.class);
  }

  @Override
  public ChannelResp findChannel(String channelCode) {
    System.out.println("channelCode=" + channelCode);
    ChannelEntity channelEntity = channelRepository.findByChannelCode(channelCode).orElse(null);
    return mapper.map(channelEntity, ChannelResp.class);
  }

  @Override
  public ChannelResp findChannel(String source, String tranType) {
    MappingResp mappingResp = mappingService.findMapping(source, tranType);
    return mapper.map(channelRepository.findById(mappingResp.getChannelId()).orElse(null), ChannelResp.class);
  }

  @Override
  public ChannelResp saveChannel(ChannelReq channelReq) {
    // System.out.println(channel.toString());
    ChannelEntity entityReq = mapper.map(channelReq, ChannelEntity.class);
    ChannelEntity entityResp = channelRepository.save(entityReq);
    System.out.println("entityResp.count=" + entityResp.getTransactions().size());
    return mapper.map(entityResp, ChannelResp.class);
  }

}
