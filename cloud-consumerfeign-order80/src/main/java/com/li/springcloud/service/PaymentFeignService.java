package com.li.springcloud.service;

import com.li.springcloud.entities.CommonResult;
import com.li.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

    @Component
    @FeignClient("CLOUD-PAYMENT-SERVICE")
    public interface PaymentFeignService {

        @GetMapping("/payment/{id}")
        public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

        @GetMapping("/payment/timeout")
        public String feignTimeout();
    }
