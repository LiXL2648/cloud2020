package com.li.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl {

    public String paymentInfo_OK(){
        return "线程池：" + Thread.currentThread().getName() + ", paymentInfo_OK";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_timeout_handler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_timeout(){
        // long millis = 5000 / 0;
        long millis = 5000;
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + ", paymentInfo_timeout, 耗时（ms）：" + millis;
    }

    public String paymentInfo_timeout_handler() {
        return "线程池：" + Thread.currentThread().getName() + ", 访问超时了 ...";
    }

    @HystrixCommand(fallbackMethod = "paymentCircultBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")//失败率达到多个后断路
    })
    public String paymentCircultBreaker(String name) {
        if (name == null) {
            throw new RuntimeException("name 不能为空");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "调用成功，编号：" + serialNumber;
    }

    public String paymentCircultBreaker_fallback(String name) {
        return "出错了，请重试";
    }
}
