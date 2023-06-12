package com.codewave.project.projectcryptochannel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewave.project.projectcryptochannel.model.ChannelCoinMapping;

public interface ChannelCoinMapRepository extends JpaRepository<ChannelCoinMapping, Long> {

  List<ChannelCoinMapping> findAll();
}
