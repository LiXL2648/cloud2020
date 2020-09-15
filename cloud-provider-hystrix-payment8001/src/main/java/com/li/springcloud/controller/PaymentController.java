package com.li.springcloud.controller;

import com.li.springcloud.service.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentService;

    @GetMapping("/payment/ok")
    public String paymentInfo_OK(){
        log.info("线程池：" + Thread.currentThread().getName() + ", paymentInfo_OK");
        return paymentService.paymentInfo_OK();
    }

    @GetMapping("/payment/timeout")
    public String paymentInfo_timeout(){
        log.info("线程池：" + Thread.currentThread().getName() + ", paymentInfo_timeout");
        return paymentService.paymentInfo_timeout();
    }

    @GetMapping("/payment/circuit")
    public String payemntCircuitBreaker(String name) {
        String result = paymentService.paymentCircultBreaker(name);
        log.info("result: " + result);
        return result;
    }
}
