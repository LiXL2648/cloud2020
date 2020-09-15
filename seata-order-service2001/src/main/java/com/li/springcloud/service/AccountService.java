package com.li.springcloud.service;

import com.li.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "seata-account-service")
public interface AccountService {

    @GetMapping("/account/decrease")
    CommonResult decrease(@RequestParam("useId") Long useId, @RequestParam("money") BigDecimal money);
}