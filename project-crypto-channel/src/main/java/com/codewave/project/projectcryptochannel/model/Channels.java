package com.codewave.project.projectcryptochannel.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Channels {
  private Long id;
  private String channelCode;
  private String channelUrl;
  private LocalDateTime lastUpdDate;
  private List<ChanllelTrans> coinTrans = new ArrayList<>();
  private List<ChannelCoinMapping> coinMap = new ArrayList<>();

}
