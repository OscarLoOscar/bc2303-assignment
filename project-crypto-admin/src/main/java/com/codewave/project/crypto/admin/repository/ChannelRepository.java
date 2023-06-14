package com.codewave.project.crypto.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewave.project.crypto.admin.entity.ChannelEntity;

public interface ChannelRepository extends JpaRepository<ChannelEntity, Long> {
  
  Optional<ChannelEntity> findByChannelCode(String channelCode);

}
