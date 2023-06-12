package com.codewave.project.projectcryptochannel.infra.util;

import lombok.Getter;

@Getter
public enum UriScheme {

  HTTP("http"),
  HTTPS("https"),
  ;

  private String protocol;

  UriScheme(String protocol) {
    this.protocol = protocol;
  }

}
