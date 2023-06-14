package com.codewave.project.crypto.admin.service;

import com.codewave.project.crypto.admin.dto.request.MappingReq;
import com.codewave.project.crypto.admin.dto.response.MappingResp;

public interface MappingService {

  MappingResp findMapping(String source, String tranType);

  MappingResp saveMapping(Long channelId, MappingReq mappingReq);

}