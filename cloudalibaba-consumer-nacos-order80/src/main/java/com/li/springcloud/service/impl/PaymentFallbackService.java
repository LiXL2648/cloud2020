package com.li.springcloud.service.impl;

import com.li.springcloud.entities.CommonResult;
import com.li.springcloud.entities.Payment;
import com.li.springcloud.service.PaymentService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {

    @Override
    public CommonResult<Payment> getPaymentById(Long id) {
        Payment payment = new Payment(id, null);
        return new CommonResult<>(404, "fallback, exception: 服务降级返回", payment);
    }
}
