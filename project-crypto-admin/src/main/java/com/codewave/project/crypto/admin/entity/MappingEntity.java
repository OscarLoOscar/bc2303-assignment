package com.codewave.project.crypto.admin.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CHANNEL_MAPS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MappingEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "SOURCE")
  private String source;
  
  @Column(name = "TRAN_TYPE")
  private String tranType;

  @Column(name = "LAST_UPD_DATE")
  private LocalDateTime lastUpdDate;

  @ManyToOne
  @JoinColumn(name = "CHANNEL_ID", nullable = false)
  @JsonBackReference
  private ChannelEntity channel;

  @PrePersist
  public void prePersist() {
    lastUpdDate = LocalDateTime.now();
  }

}