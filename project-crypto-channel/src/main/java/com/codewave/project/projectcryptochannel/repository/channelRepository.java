package com.codewave.project.projectcryptochannel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewave.project.projectcryptochannel.model.ChannelTrans;
import com.codewave.project.projectcryptochannel.model.Channels;

// Hibernate 寫法，當SQL用
//ResponseEntity -> 連database用

/**
 * 
 * SELECT *
 * FROM CHANNELS c , CHANNEL_TRANS t , CHANNEL_COIN_MAPPINGS m
 * where c.id = t.channel_id
 * and c.id = m.channel_id
 * and t.source_app = 'crypto-web'
 * and t.tran_type = 'ex-rate'
 */
public interface ChannelRepository extends JpaRepository<Channels, Long> {
  // Channels findBySourceAppAndTranType(
  //     String sourceApp,
  //     String tranType);

  List<Channels> findAll();

}
