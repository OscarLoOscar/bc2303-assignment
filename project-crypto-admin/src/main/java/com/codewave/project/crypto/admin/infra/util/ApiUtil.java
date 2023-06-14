package com.codewave.project.crypto.admin.infra.util;

import java.net.URI;
import java.util.Map;

import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import com.codewave.project.crypto.admin.infra.enums.UriScheme;

public class ApiUtil {

  /**
   * No Path Variables and No Query Parameters
   * @param scheme
   * @param domain
   * @param endpoint
   * @param pathSegments
   * @return
   */
  public static UriComponentsBuilder uriBuilder(UriScheme scheme, String domain, String endpoint,
      String... pathSegments) {
    return UriComponentsBuilder.newInstance()
        .scheme(scheme.getProtocol())
        .host(domain)
        .pathSegment(pathSegments) // add slashes automatically
        .path(endpoint);
  }

  /**
   * Path Variables Only.
   * 
   * @param scheme
   * @param domain
   * @param endpoint
   * @param pathVariables
   * @param pathSegments
   * @return
   */
  public static URI uriBuilder(UriScheme scheme, String domain, String endpoint,
      Map<String, String> pathVariables, String... pathSegments) {
    return UriComponentsBuilder.newInstance()
        .scheme(scheme.getProtocol())
        .host(domain)
        .pathSegment(pathSegments) // add slashes automatically
        .path(endpoint)
        .build(pathVariables);
  }

  /**
   * Query Parameters Only.
   * 
   * @param scheme
   * @param domain
   * @param endpoint
   * @param queryParams
   * @param pathSegments
   * @return
   */
  public static UriComponentsBuilder uriBuilder(UriScheme scheme, String domain, String endpoint,
      MultiValueMap<String, String> queryParams, String... pathSegments) {
    return UriComponentsBuilder.newInstance()
        .scheme(scheme.getProtocol())
        .host(domain)
        .pathSegment(pathSegments) // add slashes automatically
        .path(endpoint)
        .queryParams(queryParams);
  }

  /**
   * Both Path Variables and Query Parameters
   * 
   * @param scheme
   * @param domain
   * @param endpoint
   * @param pathVariables
   * @param queryParams
   * @param pathSegments
   * @return
   */
  public static URI uriBuilder(UriScheme scheme, String domain, String endpoint,
      Map<String, String> pathVariables, MultiValueMap<String, String> queryParams,
      String... pathSegments) {
    return UriComponentsBuilder.newInstance()
        .scheme(scheme.getProtocol())
        .host(domain)
        .pathSegment(pathSegments) // add slashes automatically
        .path(endpoint)
        .queryParams(queryParams)
        .build(pathVariables);
  }

  // public static UriComponentsBuilder uriBuilder(UriScheme scheme, String
  // domain, String endpoint,
  // MultiValueMap<String, String> queryParams) {
  // return UriComponentsBuilder.newInstance()
  // .scheme(scheme.getProtocol())
  // .host(domain)
  // .path(endpoint)
  // .queryParams(queryParams);
  // }

}
