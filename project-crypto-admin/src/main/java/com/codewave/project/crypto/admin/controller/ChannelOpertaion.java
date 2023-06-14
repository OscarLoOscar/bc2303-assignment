package com.codewave.project.crypto.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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

public interface ChannelOpertaion {
/**{
    "channelCode": "coingecko",
    "channelDomain": "localhost: 8085"
} */
  @PostMapping(value = "/channel")
  ChannelResp createChannel(@RequestBody ChannelReq channel);
/**{
    "id": 1,
    "source": "TEST",
    "tranType": "TESTING",
    "channelId": 1
} */
  @PostMapping(value = "/channel/{id}/mapping")
  MappingResp createMapping(@PathVariable Long id,
      @RequestBody MappingReq mappingReq);
/**{
    "domainVersion":"123",
    "domainUrl":"test",
    "sourceApp":"testing",
    "tranType":"testtest",
    "lastUpdDate":"2023-01-01T16:40:41"
} */
  @PostMapping(value = "/channel/{id}/transaction")
  TransactionResp createTransaction(@PathVariable Long id,
      @RequestBody TransactionReq transactionReq);

  @GetMapping(value = "/channel/mapping")
  ChannelResp getChannel(@RequestParam(value = "source") String source,
      @RequestParam(value = "tranType") String tranType);

  @GetMapping(value = "/channel/service")
  CoinServiceResp getCoinService(@RequestParam(value = "channelCode") String channelCode,
      @RequestParam(value = "tranType") String tranType);

}
