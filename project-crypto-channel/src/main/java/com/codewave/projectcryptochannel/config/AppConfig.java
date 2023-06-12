package com.codewave.projectcryptochannel.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class AppConfig {
  @Bean
  ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  // 进行各个类型和Json类型的相互转换
  ObjectMapper objectMapper() {
    // 可以将各种Module注册到ObjectMapper中，每个Module中可以包含多个反序列化组件
    SimpleModule simpleModule = new SimpleModule();
    // 添加将long转成String的组件
    simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
    simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(simpleModule);
    // 将时间反序列化组件添加到ObjectMapper中
    objectMapper.registerModule(new JavaTimeModule());
    // 禁止将时间类型数据转换成时间戳
    objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    return objectMapper;
  }
}
