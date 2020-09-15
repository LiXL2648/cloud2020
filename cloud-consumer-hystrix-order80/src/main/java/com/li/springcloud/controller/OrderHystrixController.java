package com.li.springcloud.controller;

import com.li.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DefaultProperties(defaultFallback = "payment_global_fallback")
public class OrderHystrixController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/ok")
    public String paymentInfo_OK() {
        return paymentHystrixService.paymentInfo_OK();
    }

    @GetMapping("/consumer/payment/timeout")
    /*@HystrixCommand(fallbackMethod = "paymentInfo_timeout_handler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })*/
    @HystrixCommand
    public String paymentInfo_timeout() {
        int i = 10 / 0;
        return paymentHystrixService.paymentInfo_timeout();
    }

    public String paymentInfo_timeout_handler() {
        return "线程池：" + Thread.currentThread().getName() + ", 访问超时了 ..., serverPort：" + serverPort;
    }

    public String payment_global_fallback() {
        return "Global 异常处理信息，请稍后再试";
    }
}
