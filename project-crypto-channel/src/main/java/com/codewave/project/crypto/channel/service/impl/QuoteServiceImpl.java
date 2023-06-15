// package com.codewave.project.crypto.channel.service.impl;

// import java.math.BigDecimal;
// import java.math.RoundingMode;
// import java.util.UUID;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;

// import com.codewave.project.crypto.channel.config.RedisConfig;
// import com.codewave.project.crypto.channel.dto.response.ChannelDto;
// import com.codewave.project.crypto.channel.dto.response.ChannelDto.ExchangeRate;
// import com.codewave.project.crypto.channel.dto.response.Exchange.ExchangeReq;
// import com.codewave.project.crypto.channel.infra.enums.Currency;
// import com.codewave.project.crypto.channel.service.AdminService;
// import com.codewave.project.crypto.channel.service.ExchangeService;
// import com.codewave.project.crypto.channel.service.QuoteService;

// @Service
// public class QuoteServiceImpl implements QuoteService {

//   @Autowired
//   RedisConfig redisConfig;

//   @Autowired
//   RestTemplate restTemplate;

//   @Autowired
//   AdminService adminService;

//   @Autowired
//   ExchangeService exchangeService;
//   // @Autowired
//   // RedisTemplate<String, ExchangeReq> redisTemplate;

//   @Override
//   public ExchangeReq getQuote(String fromCurr, BigDecimal fromCurrQuantity, String toCurr) {
//     // Generate a UUID for the response
//     UUID uuid = UUID.randomUUID();
//     // // Call the external API to get the exchange rate
//     BigDecimal exchangeRate = callExternalApi(fromCurr, toCurr);
//     // // Calculate the toCurrQuantity
//     BigDecimal toCurrQuantity = fromCurrQuantity.multiply(exchangeRate);

//     // Create the ExchangeReq object
//     ExchangeReq exchangeReq = ExchangeReq.builder()
//         .fromCurr(fromCurr)
//         .fromCurrQuantity(fromCurrQuantity)
//         .toCurr(toCurr)
//         .toCurrQuantity(toCurrQuantity)
//         .build();
//     // Store the ExchangeReq object in Redis with a 10-minute expiry
//     // redisTemplate.opsForValue().set(uuid.toString(), exchangeReq,
//     // java.time.Duration.ofMinutes(10));
//     // Return the ExchangeReq object
//     return exchangeReq;
//   }

//   private BigDecimal callExternalApi(String fromCurr, String toCurr) {
//     return BigDecimal.valueOf(0.05);
//     // Call the exchange method of the ExchangeService to get the exchange rate
//     // ChannelDto.ExchangeRate exchangeRate;

//     exchangeService.exchange(Currency.valueOf(fromCurr),
//         Currency.valueOf(toCurr));
//     // Get the exchange rate from the ChannelDto object
//     BigDecimal exchangeRateValue = ChannelDto.ExchangeRate.getRate().setScale(2,
//         RoundingMode.HALF_UP);

//   }

// }