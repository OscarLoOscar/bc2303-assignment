package com.codewave.project.projectcryptochannel.service;

import java.util.List;
import java.util.Optional;

import com.codewave.project.projectcryptochannel.model.ChannelTrans;
import com.codewave.project.projectcryptochannel.model.Channels;

public interface ChannelService {

  Channels save(Channels channels);

  List<Channels> getAll();

  Channels addByChannelId(Channels channel, Long id);

  List<Channels> findAll();

  Optional<Channels> get(Long id);

  ChannelTrans save(ChannelTrans channelTrans);

  List<ChannelTrans> findAllTransaction();

  List<ChannelTrans> getTransaction(String source, String tranType);
}
