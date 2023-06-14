package com.codewave.project.crypto.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewave.project.crypto.admin.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

  // List<TransactionEntity> findAllBySourceAndTranType(String source, String tranType);

  List<TransactionEntity> findByChannelId(Long channelId);

}
