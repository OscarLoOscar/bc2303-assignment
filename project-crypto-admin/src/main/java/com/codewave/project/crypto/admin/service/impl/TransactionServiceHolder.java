package com.codewave.project.crypto.admin.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewave.project.crypto.admin.dto.request.TransactionReq;
import com.codewave.project.crypto.admin.dto.response.ChannelResp;
import com.codewave.project.crypto.admin.dto.response.CoinServiceResp;
import com.codewave.project.crypto.admin.dto.response.TransactionResp;
import com.codewave.project.crypto.admin.entity.ChannelEntity;
import com.codewave.project.crypto.admin.entity.TransactionEntity;
import com.codewave.project.crypto.admin.repository.ChannelRepository;
import com.codewave.project.crypto.admin.repository.TransactionRepository;
import com.codewave.project.crypto.admin.service.TransactionService;

@Service
public class TransactionServiceHolder implements TransactionService {

  @Autowired
  ChannelRepository channelRepository;

  @Autowired
  TransactionRepository transactionRepository;

  @Autowired
  ModelMapper mapper;
  
  @Override
  public TransactionResp saveTransaction(Long channelId, TransactionReq transactionReq) {
    if (transactionReq == null || channelId == null)
      return null;
    ChannelEntity channelEntity = channelRepository.findById(channelId).orElse(null);
    TransactionEntity transactionEntity = mapper.map(transactionReq, TransactionEntity.class);
    transactionEntity.setChannel(channelEntity);
    return mapper.map(transactionRepository.save(transactionEntity), TransactionResp.class);
  }

}
