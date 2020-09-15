package com.li.springcloud.dao;

import com.li.springcloud.entities.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {

    // 新建订单
    void create(Order order);

    // 修改订单状态，从 0 改为 1
    void update(Long id);
}
