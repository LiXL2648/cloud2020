package com.li.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements  PaymentHystrixService {
    @Override
    public String paymentInfo_OK() {
        return "PaymentFallbackService ... paymentInfo_OK ... fallback";
    }

    @Override
    public String paymentInfo_timeout() {
        return "PaymentFallbackService ... paymentInfo_timeout ... fallback";
    }
}
