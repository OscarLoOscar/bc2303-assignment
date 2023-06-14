package com.codewave.project.crypto.admin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO should be immutable.
 * 
 * @author vincent.lau
 */
@Getter
@Setter // for modelmapper
@AllArgsConstructor
@NoArgsConstructor
public class MappingResp {

  private Long id;

  private String source;

  private String tranType;

  private Long channelId;

}
