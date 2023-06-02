package com.codewave.project.projectcryptocoingecko.infra.util;

import org.springframework.web.util.UriComponentsBuilder;

public class ApiUtil {

  public static String getUrl(UriScheme scheme, String domain,String serverPort, String endpoint) {
    return UriComponentsBuilder.newInstance()
        .scheme(scheme.getProtocol())
        .host(domain)
        .path(serverPort) //add
        .path(endpoint)
        .toUriString();
  }

}
