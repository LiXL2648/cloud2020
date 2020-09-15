package com.li.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.li.springcloud.entities.CommonResult;
import com.li.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200, "按资源名称限流OK", new Payment(1L, "940906"));
    }
    public CommonResult handleException(BlockException e) {
        return new CommonResult(500, e.getClass().getCanonicalName() + ", 服务不可用");
    }

    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl")
    public  CommonResult byUrl() {
        return new CommonResult(200, "按Url限流OK",  new Payment(2L, "921127"));
    }

    @GetMapping("/testA")
    public String testA() {
        return "testA";
    }

    @GetMapping("/testB")
    public String testB() {
        log.info("testB，测试RT");
        return "testB";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_hotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                              @RequestParam(value = "p2", required = false) String p2) {
        return "testHotKey";
    }

    public String deal_hotKey(String p1, String p2, BlockException e) {
        return "deal_hotKey";
    }
}
