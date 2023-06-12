package com.codewave.project.projectcryptochannel.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.codewave.project.projectcryptochannel.model.ChannelCoinMapping;
import com.codewave.project.projectcryptochannel.model.ChannelTrans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelsRespDto {

  private String channelCode;

  private String channelUrl;

  private LocalDateTime lastUpdDate;

  private List<ChannelCoinMapping> coinMaps = new ArrayList<>();

  private List<ChannelTrans> coinTrans = new ArrayList<>();

}
