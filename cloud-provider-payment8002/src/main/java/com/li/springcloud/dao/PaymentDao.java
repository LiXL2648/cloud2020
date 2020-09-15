package com.li.springcloud.dao;

import com.li.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentDao {

    public int addPayment(Payment payment);

    public Payment getPaymentById(Long id);
}
