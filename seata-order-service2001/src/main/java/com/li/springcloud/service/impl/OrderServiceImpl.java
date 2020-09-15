package com.li.springcloud.service.impl;

import com.li.springcloud.dao.OrderDao;
import com.li.springcloud.entities.Order;
import com.li.springcloud.service.AccountService;
import com.li.springcloud.service.OrderService;
import com.li.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private StorageService storageService;

    @Autowired
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "seata-create-order", rollbackFor = Exception.class)
    public void create(Order order) {
        // 1. 新建订单
        log.info("开始新建订单");
        orderDao.create(order);

        // 2. 扣减库存
        log.info("订单微服务开始调用库存，做扣减Count");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("订单微服务开始调用库存，做扣减Count完成");

        // 3. 扣减余额
        log.info("订单微服务开始调用账户，做扣减Money");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("订单微服务开始调用账户，做扣减Money完成");

        // 4. 修改订单状态，将订单状态status从 0 改为 1
        log.info("修改订单状态，将订单状态status从 0 改为 1");
        orderDao.update(order.getId());
        log.info("修改订单状态，将订单状态status从 0 改为 1完成");

        log.info("新建订单完成");
    }
}
