package com.li.springcloud.service;

import com.li.springcloud.entities.Order;

public interface OrderService {

    // 新建订单
    void create(Order order);
}
