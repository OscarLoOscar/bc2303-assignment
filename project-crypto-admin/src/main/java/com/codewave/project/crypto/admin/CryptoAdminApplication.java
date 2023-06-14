package com.codewave.project.crypto.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.codewave.project.crypto.admin.dto.request.ChannelReq;
import com.codewave.project.crypto.admin.dto.request.MappingReq;
import com.codewave.project.crypto.admin.dto.request.TransactionReq;
import com.codewave.project.crypto.admin.service.ChannelService;
import com.codewave.project.crypto.admin.service.MappingService;
import com.codewave.project.crypto.admin.service.TransactionService;

@SpringBootApplication
public class CryptoAdminApplication implements CommandLineRunner {

	@Autowired
	ChannelService channelService;

	@Autowired
	TransactionService transactionService;

	@Autowired
	MappingService mappingService;

	public static void main(String[] args) {
		SpringApplication.run(CryptoAdminApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		channelService.saveChannel(new ChannelReq("COINGECKO", "localhost:8085"));
		channelService.saveChannel(new ChannelReq("POLYGON", "localhost:8086"));
		transactionService.saveTransaction(1L,
				new TransactionReq("EXCHANGE", "crypto/coingecko/v1", "/exchange"));
		transactionService.saveTransaction(2L,
				new TransactionReq("EXCHANGE", "crypto/polygon/v1", "/exchange"));
		mappingService.saveMapping(1L, new MappingReq("WEB", "EXCHANGE"));
		mappingService.saveMapping(2L, new MappingReq("MOB", "EXCHANGE"));
	}

}
