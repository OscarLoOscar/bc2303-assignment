package com.codewave.projectcryptopolygon.infra.util;

import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

public class ApiUtil {

  // overload
  public static String getUrl(UriScheme scheme, String domain, String endpoint) {
    return UriComponentsBuilder.newInstance()
        .scheme(scheme.getProtocol())
        .host(domain)
        .path(endpoint)
        .toUriString();
  }

  public static String getUrl(UriScheme scheme, String domain, String endpoint, String... pathSegments) {
    return UriComponentsBuilder.newInstance()
        .scheme(scheme.getProtocol())
        .host(domain)
        .pathSegment(pathSegments) // add slashes automatically
        .path(endpoint)
        .toUriString();
  }

  public static String getUrl(UriScheme scheme, String domain, String endpoint,
      MultiValueMap<String, String> queryParams) {
    return UriComponentsBuilder.newInstance()
        .scheme(scheme.getProtocol())
        .host(domain)
        .path(endpoint)
        .queryParams(queryParams)
        .toUriString();
  }

  public static String getUrl(UriScheme scheme, String domain, String endpoint,
      String pathSegment, String version, MultiValueMap<String, String> queryParams) {
    return UriComponentsBuilder.newInstance()
        .scheme(scheme.getProtocol())
        .host(domain)
        .path(endpoint)
        .queryParams(queryParams)
        .toUriString();
  }

  public static String getUrl(UriScheme scheme, String domain, String endpoint,
      MultiValueMap<String, String> queryParams, String... pathSegments) {
    return UriComponentsBuilder.newInstance()
        .scheme(scheme.getProtocol())
        .host(domain)
        .pathSegment(pathSegments) // add slashes automatically
        .path(endpoint)
        .queryParams(queryParams)
        .toUriString();
  }

}
