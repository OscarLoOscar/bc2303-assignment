package com.codewave.project.projectcryptochannel.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.codewave.project.projectcryptochannel.model.ChannelCoinMapping;
import com.codewave.project.projectcryptochannel.model.ChannelTrans;
import com.codewave.project.projectcryptochannel.model.Channels;

public interface ChannelOperation {

        @GetMapping(value = "/channel/{id}")
        Channels getChannel(@PathVariable Long id);

        /**
         * {
         * "channelCode": "coingecko",
         * "channelUrl": "localhost: 8085",
         * "lastUpdDate": "2023-06-08T16:40:41"
         * }
         */
        @PostMapping(value = "/channel/transaction")
        Channels createChannel(@RequestBody Channels channel);

        /**
         * {
         * "domainVersion":"123",
         * "domainUrl":"test",
         * "sourceApp":"testing",
         * "tranType":"testtest",
         * "lastUpdDate":"2023-01-01T16:40:41"
         * }
         */
        @PostMapping(value = "/channel/{channelId}/transaction")
        ChannelTrans createTransaction(@PathVariable Long channelId,
                        @RequestBody ChannelTrans channelTrans);

        @GetMapping(value = "/channel/transactions")
        List<ChannelTrans> getTransaction(@RequestParam(value = "source") String source,
                        @RequestParam(value = "tranType") String tranType);

        @PostMapping(value = "/channel/{channelId}/coin")
        ChannelCoinMapping createCoin(@PathVariable Long channelId,
                        @RequestBody ChannelCoinMapping channelCoinMapping);

        @GetMapping(value = "/channel/coins")
        List<ChannelCoinMapping> getCoin(@RequestParam(value = "tranType") String tranType);

}
