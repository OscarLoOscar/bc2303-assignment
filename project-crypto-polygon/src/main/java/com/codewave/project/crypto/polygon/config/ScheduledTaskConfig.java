package com.codewave.project.crypto.polygon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.codewave.project.crypto.polygon.service.PreviousCloseService;

@Configuration
@EnableScheduling // inject some bean(s) into spring context
public class ScheduledTaskConfig {

  @Autowired
  PreviousCloseService previousCloseService;

  @Scheduled(fixedRate = 60000) // ms
  public void scheduleFixedRateTask() throws Exception {
    System.out.println("Fixed rate task - " + System.currentTimeMillis() / 1000);
  }
  
}