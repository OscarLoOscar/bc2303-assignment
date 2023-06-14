package com.codewave.project.crypto.admin.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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

@Entity
@Table(name = "CHANNELS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "CHANNEL_CODE")
  private String channelCode;

  @Column(name = "CHANNEL_DOMAIN")
  private String channelDomain;

  @Column(name = "LAST_UPD_DATE")
  private LocalDateTime lastUpdDate;

  @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private final List<TransactionEntity> transactions = new ArrayList<>();

  @PrePersist
  public void prePersist() {
    lastUpdDate = LocalDateTime.now();
  }

  public boolean addTransaction(TransactionEntity transaction) {
    return transactions.add(transaction);
  }

}
