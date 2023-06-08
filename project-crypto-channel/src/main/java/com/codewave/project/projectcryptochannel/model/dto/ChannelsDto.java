package com.codewave.project.projectcryptochannel.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChannelsDto {

  private String channelCode;
  private String channelUrl;
  private LocalDateTime lastUpdDate;

}
