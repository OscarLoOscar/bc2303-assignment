package com.codewave.project.projectcryptochannel.config;

import java.nio.channels.Channels;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisTemplateConfig {
  @Bean
  public RedisTemplate<String, Channels> redisTemplate(RedisConnectionFactory connectionFactory) {
    RedisTemplate<String, Channels> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(connectionFactory);
    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
    redisTemplate.setKeySerializer(stringRedisSerializer);
    return redisTemplate;
  }
}
