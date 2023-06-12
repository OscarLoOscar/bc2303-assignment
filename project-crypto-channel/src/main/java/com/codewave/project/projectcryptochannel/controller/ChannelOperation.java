package com.codewave.project.projectcryptochannel.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.codewave.project.projectcryptochannel.model.ChannelTrans;
import com.codewave.project.projectcryptochannel.model.Channels;

public interface ChannelOperation {

        @GetMapping(value = "/channel/{id}")
        Channels getChannel(@PathVariable Long id);

        @PostMapping(value = "/channel/transaction")
        Channels createChannel(@RequestBody Channels channel);

        @PostMapping(value = "/channel/{channelId}/transaction")
        ChannelTrans createTransaction(@PathVariable Long channelId,
                        @RequestBody ChannelTrans channelTrans);

        @GetMapping(value = "/channel/transactions")
        List<ChannelTrans> getTransaction(@RequestParam(value = "source") String source,
                        @RequestParam(value = "tranType") String tranType);

}
