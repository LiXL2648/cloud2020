package com.li.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String PAYMENT_URL = "http://cloud-provider-payment";

    @GetMapping("/order/serverPort")
    public String getServerPort() {
        String result = restTemplate.getForObject(PAYMENT_URL + "/serverPort", String.class);
        return  result;
    }
}
