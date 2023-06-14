package com.codewave.project.crypto.coingecko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication // component
public class CoingeckoApplication  {

	public static void main(String[] args) {
		// temp fix
		// https://stackoverflow.com/questions/57750294/class-loader-error-unnamed-module-of-loader-org-springframework-boot-devtools
		//System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(CoingeckoApplication.class, args);
	}

}
