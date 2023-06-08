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

  public static String getUrl(UriScheme scheme, String domain, String version, String endPoint,
      MultiValueMap<String, String> queryParams, String... pathSegments) {
    return UriComponentsBuilder.newInstance()
        .scheme(scheme.getProtocol())
        .host(domain)
        .path(version)
        .path(endPoint)
        .pathSegment(pathSegments)
        .queryParams(queryParams)
        .toUriString();
  }

  public static String getUrl(UriScheme scheme, String domain, String version, String endpoint,
      String cryptoTicker, String string, String multiplier, String timespan, String from, String to,
      MultiValueMap<String, String> queryParams) {
    return UriComponentsBuilder.newInstance()
        .scheme(scheme.getProtocol())
        .host(domain)
        .path(version)
        .path(endpoint)
        .path(cryptoTicker)
        .path(string)
        .path(multiplier)
        .path(timespan)
        .path(from)
        .path(to)
        .queryParams(queryParams)
        .toUriString();
  }

  public static String getUrl(UriScheme scheme, String domain, String version, String endpoint, String path,
      String cryptoTicker, String string, MultiValueMap<String, String> queryParams) {
        return UriComponentsBuilder.newInstance()
        .scheme(scheme.getProtocol())
        .host(domain)
        .path(version)
        .path(endpoint)
        .path(path)
        .path(cryptoTicker)
        .path(string)
        .queryParams(queryParams)
        .toUriString();
  }
}
