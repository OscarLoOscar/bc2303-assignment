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
public class ChanllelTrans {
  private Long id;
  private String domainVersion;
  private String domainUrl;
  private String sourceApp;
  private String tranType;
  private LocalDateTime lastUpdTime;
  private Channels channel;
}
