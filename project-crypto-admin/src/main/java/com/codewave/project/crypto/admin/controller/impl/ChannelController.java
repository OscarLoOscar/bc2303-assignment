package com.codewave.project.crypto.admin.controller.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewave.project.crypto.admin.controller.ChannelOpertaion;
import com.codewave.project.crypto.admin.dto.request.ChannelReq;
import com.codewave.project.crypto.admin.dto.request.MappingReq;
import com.codewave.project.crypto.admin.dto.request.TransactionReq;
import com.codewave.project.crypto.admin.dto.response.ChannelResp;
import com.codewave.project.crypto.admin.dto.response.CoinServiceResp;
import com.codewave.project.crypto.admin.dto.response.MappingResp;
import com.codewave.project.crypto.admin.dto.response.TransactionResp;
import com.codewave.project.crypto.admin.infra.enums.ChannelCode;
import com.codewave.project.crypto.admin.infra.enums.Source;
import com.codewave.project.crypto.admin.infra.enums.Trantype;
import com.codewave.project.crypto.admin.mapper.ChannelMapper;
import com.codewave.project.crypto.admin.service.ChannelService;
import com.codewave.project.crypto.admin.service.MappingService;
import com.codewave.project.crypto.admin.service.TransactionService;

@RestController
@RequestMapping(value = "/crypto/admin/v1")
public class ChannelController implements ChannelOpertaion {

  @Autowired
  private ChannelService channelService;

  @Autowired
  private MappingService mappingService;

  @Autowired
  private TransactionService channelTransactionService;

  @Override
  public ChannelResp createChannel(ChannelReq channelReq) {
    return channelService.saveChannel(channelReq);
  }

  @Override
  public MappingResp createMapping(Long id, MappingReq mappingReq) {
    return mappingService.saveMapping(id, mappingReq);
  }

  @Override
  public TransactionResp createTransaction(Long id, TransactionReq transactionReq) {
    return channelTransactionService.saveTransaction(id, transactionReq);
  }

  @Override
  public ChannelResp getChannel(String source, String tranType) {
    return channelService.findChannel(source, tranType);
  }

  @Override
  public CoinServiceResp getCoinService(String channelCode, String tranType) {
    if (channelCode == null || tranType == null)
      return null;

    ChannelResp channelResp = channelService.findChannel(channelCode);

    TransactionResp transactionResp = channelResp.getTransactions().stream()
        .filter(e -> tranType.equals(e.getTranType()))
        .findFirst().orElse(null);

    return ChannelMapper.map(transactionResp, channelResp);
  }

}
