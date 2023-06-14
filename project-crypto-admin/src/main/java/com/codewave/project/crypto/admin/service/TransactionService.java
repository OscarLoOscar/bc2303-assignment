package com.codewave.project.crypto.admin.service;

import com.codewave.project.crypto.admin.dto.request.TransactionReq;
import com.codewave.project.crypto.admin.dto.response.TransactionResp;

public interface TransactionService {

  // List<CoinServiceResp> findTransactions(String source, String tranType);

  TransactionResp saveTransaction(Long channelId, TransactionReq transaction);

}
