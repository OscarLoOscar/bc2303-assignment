package com.codewave.project.projectcryptochannel.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "CHANNEL_TRANS")
public class ChannelTrans implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Nonnull
  @Column(name = "DOMAIN_VERSION")
  private String domainVersion;

  @Nonnull
  @Column(name = "DOMAIN_URL")
  private String domainUrl;

  @Nonnull
  @Column(name = "SOURCE_APP")
  private String sourceApp;

  @Nonnull
  @Column(name = "TRAN_TYPE")
  private String tranType;

  @Nonnull
  @Column(name = "LAST_UPD_DATE")
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") // 重要
  private LocalDateTime lastUpdDate;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "channel_id", referencedColumnName = "id")
  @JsonIgnoreProperties(ignoreUnknown = true)
  private Channels channel;

}