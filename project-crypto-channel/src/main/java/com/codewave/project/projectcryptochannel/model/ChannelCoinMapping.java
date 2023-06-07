package com.codewave.project.projectcryptochannel.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChannelCoinMapping {
  private Long id;
  private String coinCode;
  private String coinId;
  private LocalDateTime lastUpdDate;
  private Channels channel;
}
