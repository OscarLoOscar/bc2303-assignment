package com.codewave.project.crypto.admin.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewave.project.crypto.admin.dto.request.MappingReq;
import com.codewave.project.crypto.admin.dto.response.MappingResp;
import com.codewave.project.crypto.admin.entity.ChannelEntity;
import com.codewave.project.crypto.admin.entity.MappingEntity;
import com.codewave.project.crypto.admin.infra.enums.Source;
import com.codewave.project.crypto.admin.repository.ChannelRepository;
import com.codewave.project.crypto.admin.repository.MappingRepository;
import com.codewave.project.crypto.admin.service.MappingService;

@Service
public class MappingServiceHolder implements MappingService {

  @Autowired
  MappingRepository mappingRepository;

  @Autowired
  ChannelRepository channelRepository;

  @Autowired
  ModelMapper mapper;

  @Override
  public MappingResp findMapping(String source, String tranType) {
    MappingEntity entityResp = mappingRepository.findBySourceAndTranType(source, tranType);
    return mapper.map(entityResp, MappingResp.class);
  }

  @Override
  public MappingResp saveMapping(Long channelId, MappingReq mappingReq) {
    if (mappingReq == null || channelId == null)
      return null;
    ChannelEntity channelEntity = channelRepository.findById(channelId).orElse(null);
    MappingEntity mappingEntity = mapper.map(mappingReq, MappingEntity.class);
    mappingEntity.setChannel(channelEntity);
    return mapper.map(mappingRepository.save(mappingEntity), MappingResp.class);
  }

}
