package com.codewave.projectcryptopolygon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.codewave.projectcryptopolygon.infra.util.RedisUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class RedisConfig {

  /**
   * 实例化 RedisTemplate 对象
   * 提供给 RedisUtil 使用
   * 
   * @param redisConnectionFactory springboot配置好的连接工厂
   * @return RedisTemplate
   * @autor zyj
   * @date 2018年5月26日 08:47:27
   */
  @Bean
  public RedisTemplate<String, Object> RedisTemplate(RedisConnectionFactory redisConnectionFactory) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    initRedisTemplate(redisTemplate, redisConnectionFactory);
    return redisTemplate;
  }

  /**
   * 设置数据存入 redis 的序列化方式,并开启事务
   *
   * @param redisTemplate
   * @param factory
   * @autor zyj
   * @date 2018年5月26日 08:47:27
   */
  private void initRedisTemplate(RedisTemplate<String, Object> redisTemplate,
      RedisConnectionFactory factory) {
    // 如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to
    // String！

    ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder().failOnEmptyBeans(false)
        .failOnUnknownProperties(false)
        .indentOutput(false)
        .serializationInclusion(JsonInclude.Include.NON_NULL)
        .modules(
            // Optional
            new Jdk8Module(),
            // Dates/Times
            new JavaTimeModule())
        .featuresToDisable(
            SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
            DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS,
            SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
        .build();
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
    // 开启事务
    redisTemplate.setEnableTransactionSupport(true);
    redisTemplate.setConnectionFactory(factory);
    redisTemplate.afterPropertiesSet();
  }

  /**
   * 注入封装RedisTemplate 给RedisUtil提供操作类
   * 
   * @param redisTemplate
   * @return RedisUtil
   * @autor zyj
   * @date 2018年5月26日 08:47:27
   */
  @Bean
  public RedisUtil<Object> redisUtil(RedisTemplate<String, Object> redisTemplate) {
    return RedisUtil.of(redisTemplate);
  }

}