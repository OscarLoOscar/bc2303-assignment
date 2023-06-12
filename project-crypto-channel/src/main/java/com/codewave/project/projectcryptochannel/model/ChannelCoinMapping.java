package com.codewave.project.projectcryptochannel.model;

import java.io.Serializable;
import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "CHANNEL_COIN_MAPPINGS")
public class ChannelCoinMapping implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Nonnull
  @Column(name = "COIN_CODE")
  private String coinCode;

  @Nonnull
  @Column(name = "COIN_ID")
  private String coinId;

  @Nonnull
  @Column(name = "LAST_UPD_DATE")
  private LocalDateTime lastUpdDate;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "channel_id" , referencedColumnName = "id")
@JsonIgnoreProperties(ignoreUnknown = true)
  private Channels channel;
}
