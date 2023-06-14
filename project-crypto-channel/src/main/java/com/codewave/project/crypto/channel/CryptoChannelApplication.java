package com.codewave.project.crypto.channel;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CryptoChannelApplication {

	private static ApplicationContext applicationContext;
	private static List<String> contextBeans;

	public static void printBeans() {
		for (String bean : contextBeans) {
			System.out.println(bean);
		}
	}

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(CryptoChannelApplication.class, args);
		// Get All Beans From Spring Context and add into static String List
		contextBeans = Arrays.asList(applicationContext.getBeanDefinitionNames());
		// printBeans();
	}

}
