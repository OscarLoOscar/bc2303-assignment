package com.codewave.project.projectcryptochannel.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CHANNELS")
public class Channels implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  @Column(name = "CHANNEL_CODE")
  private String channelCode;

  @NonNull
  @Column(name = "CHANNEL_URL")
  private String channelUrl;

  @NonNull
  @Column(name = "LAST_UPD_DATE")
  private LocalDateTime lastUpdDate;

  @OneToMany(mappedBy = "channel")
  @JsonIgnoreProperties({ "channel" })
  private List<ChannelCoinMapping> coinMaps = new ArrayList<>();

  @OneToMany(mappedBy = "channel")
  @JsonIgnoreProperties({ "channel" })
  private List<ChannelTrans> coinTrans = new ArrayList<>();

  @PrePersist
  public void prePersist() {
    lastUpdDate = LocalDateTime.now();

  }
}
