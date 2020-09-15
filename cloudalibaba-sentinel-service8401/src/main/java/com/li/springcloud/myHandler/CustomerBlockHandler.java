package com.li.springcloud.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.li.springcloud.entities.CommonResult;
import com.li.springcloud.entities.Payment;

public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException e) {
        return new CommonResult(404, "按客户自定义，global handlerException");
    }

    public static CommonResult handlerException2(BlockException e) {
        return new CommonResult(404, "按客户自定义，global handlerException2");
    }
}
