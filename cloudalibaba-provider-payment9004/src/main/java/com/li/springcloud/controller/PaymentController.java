package com.li.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.li.springcloud.entities.CommonResult;
import com.li.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    private static Map<Long, Payment> map = new HashMap<>();

    static {
        map.put(1L, new Payment(1L, "921127"));
        map.put(2L, new Payment(2L, "940906"));
        map.put(3L, new Payment(3L, "960504"));
    }

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = map.get(id);
        return new CommonResult<>(200, "from mysql, serverPort：" + serverPort, payment);
    }
}
