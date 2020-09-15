package com.li.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("{com.li.springcloud.dao}")
public class MyBatisConfig {
}
