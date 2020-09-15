package com.li.springcloud.service;

import com.li.springcloud.entities.Payment;

public interface PaymentService {

    public int addPayment(Payment payment);

    public Payment getPaymentById(Long id);
}
