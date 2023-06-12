package com.codewave.project.projectcryptochannel.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

@Configuration
public class StringToListConverter implements Converter<String, List<String>> {

  // Spring's default WebDataBinder is configured to split parameters lists on
  // commas.
  @Override
  @Nullable
  public List<String> convert(String source) {
    return Arrays.asList(source.split(";"));
  }

}
