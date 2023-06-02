package com.codewave.project.projectcryptocoingecko.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewave.project.projectcryptocoingecko.service.impl.RedisService;


@RestController
@RequestMapping(value = "/redis/v1")
public class RedisController {

  @Autowired
  RedisService redisService;

  // // http://localhost:8091/redis/v1/test/19
  // @PostMapping(value = "/test/key/{key}/value/{value}")
  // public ApiResponse<Void> set(@PathVariable(value = "key") String key,
  //     @PathVariable(value = "value") String value) {
  //   redisService.set(key, value);
  //   return ApiResponse.<Void>builder()
  //       .ok()
  //       .data(null)
  //       .build();
  // }

  // @GetMapping(value = "/test/{key}")
  // public ApiResponse<String> get(@PathVariable(value = "key") String key) {
  //   return ApiResponse.<String>builder()
  //       .ok()
  //       .data((String) redisService.get(key))
  //       .build();
  // }
}
