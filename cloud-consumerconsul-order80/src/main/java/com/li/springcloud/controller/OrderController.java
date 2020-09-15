package com.li.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String PAYMENT_URL = "http://consul-provider-payment";

    @GetMapping("consumer/serverPort")
    public String getServerPort() {
        return restTemplate.getForObject(PAYMENT_URL + "/serverPort", String.class);
    }
}
