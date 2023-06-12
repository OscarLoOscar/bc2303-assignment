package com.codewave.project.projectcryptochannel.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewave.project.projectcryptochannel.model.ChannelCoinMapping;
import com.codewave.project.projectcryptochannel.model.ChannelTrans;
import com.codewave.project.projectcryptochannel.model.Channels;
import com.codewave.project.projectcryptochannel.repository.ChannelCoinMapRepository;
import com.codewave.project.projectcryptochannel.repository.ChannelRepository;
import com.codewave.project.projectcryptochannel.repository.ChannelTransRepository;
import com.codewave.project.projectcryptochannel.service.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService {

  @Autowired
  ChannelRepository channelRepository;

  @Autowired
  ChannelTransRepository channelTransRepository;

  @Autowired
  ChannelCoinMapRepository channelCoinMapRepository;

  @Override
  public List<Channels> getAll() {
    return channelRepository.findAll();
  }

  @Override
  public Channels addByChannelId(Channels channel, Long id) {
    if (id == null || channel == null) {
      return null;
    }
    return channelRepository.findById(id)
        .map(e -> {
          e.setChannelCode(channel.getChannelCode());
          e.setChannelUrl(channel.getChannelUrl());
          e.setLastUpdDate(channel.getLastUpdDate());
          return channelRepository.save(e);
        }).orElseGet(() -> {
          return null;
        });
  }

  @Override
  public Channels save(Channels channels) {
    return channelRepository.save(channels);
  }

  @Override
  public ChannelTrans save(ChannelTrans channelTrans) {
    ChannelTrans transaction = new ChannelTrans();
    transaction.setChannel(channelTrans.getChannel());
    transaction.setDomainUrl(channelTrans.getDomainUrl());
    transaction.setDomainVersion(channelTrans.getDomainVersion());
    transaction.setLastUpdDate(channelTrans.getLastUpdDate());
    transaction.setSourceApp(channelTrans.getSourceApp());
    transaction.setTranType(channelTrans.getTranType());

    return channelTransRepository.save(channelTrans);
  }

  @Override
  public List<Channels> findAll() {
    return channelRepository.findAll();
  }

  @Override
  public Optional<Channels> get(Long id) {
    return channelRepository.findById(id);
  }

  @Override
  public List<ChannelTrans> getTransaction(String source, String tranType) {
    return channelTransRepository.findBySourceAppAndTranType(source, tranType);
  }

  @Override
  public List<ChannelTrans> findAllTransaction() {
    return channelTransRepository.findAll();
  }

  @Override
  public ChannelCoinMapping save(ChannelCoinMapping channelCoinMapping) {
    ChannelCoinMapping coinMapping = new ChannelCoinMapping();
    coinMapping.setChannel(channelCoinMapping.getChannel());
    coinMapping.setCoinCode(channelCoinMapping.getCoinCode());
    coinMapping.setLastUpdDate(channelCoinMapping.getLastUpdDate());
    return channelCoinMapRepository.save(channelCoinMapping);
  }
}