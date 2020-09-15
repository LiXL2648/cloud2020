package com.li.springcloud.controller;

import com.li.springcloud.entities.CommonResult;
import com.li.springcloud.entities.Payment;
import com.li.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment")
    public CommonResult<Integer> addPayment(@RequestBody Payment payment) {
        int data = paymentService.addPayment(payment);
        CommonResult<Integer> result = null;
        if (data > 0){
            result = new CommonResult<>(200, "添加成功，serverPort：" + serverPort, data);
        } else {
            result = new CommonResult<>(500, "添加失败，serverPort：" + serverPort,null);
        }
        return result;
    }

    @GetMapping({"/payment/{id}", "/payment/get/{id}"})
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        CommonResult<Payment> result = null;
        if (payment == null) {
            result = new CommonResult<>(404, id + "号支付信息不存在，serverPort：" + serverPort,null);
        } else {
            result = new CommonResult<>(200, "查询成功，serverPort：" + serverPort, payment);
        }
        return result;
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }
}