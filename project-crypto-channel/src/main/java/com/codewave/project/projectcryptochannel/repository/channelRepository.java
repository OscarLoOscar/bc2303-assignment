package com.codewave.project.projectcryptochannel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewave.project.projectcryptochannel.model.Channels;
// Hibernate 寫法，當SQL用
public interface channelRepository extends JpaRepository<Channels, Long> {
  public List<Channels> findAll();
}
