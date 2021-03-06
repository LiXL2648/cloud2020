package com.li.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/serverPort")
    public String getServerPort() {
        return "springcloud with zookeeper: " + serverPort + ", "
                + UUID.randomUUID().toString().substring(0, 5);
    }
}
