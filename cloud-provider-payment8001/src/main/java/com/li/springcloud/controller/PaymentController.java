package com.li.springcloud.controller;

import com.li.springcloud.entities.CommonResult;
import com.li.springcloud.entities.Payment;
import com.li.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/discovery")
    public DiscoveryClient discovery() {

        // 获取服务清单列表
        List<String> services = discoveryClient.getServices();
        for (String service: services) {
            log.info("-------->" + service);
        }

        // 可以根据微服务具体的名称进一步获取微服务的相关信息
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance:instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort());
        }
        return discoveryClient;
    }

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

    @GetMapping(value = {"/payment/{id}", "/payment/get/{id}"})
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

    @GetMapping("/payment/timeout")
    public String feignTimeout() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin() {
        return "paymentZipkin";
    }
}
