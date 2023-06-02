package com.codewave.project.projectcryptocoingecko;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ProjectCryptoCoingeckoApplication {

	private static ApplicationContext applicationContext;
	private static List<String> contextBeans;

	public static List<String> getContextBeans() {
		return contextBeans;
	}

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(ProjectCryptoCoingeckoApplication.class, args);
		// Get All Beans From Spring Context and add into static String List
		contextBeans = Arrays.asList(applicationContext.getBeanDefinitionNames());

	}

}
