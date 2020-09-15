package com.li.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        // https://news.baidu.com/guonei
        routes.route("payment_route3", r -> r.path("/guonei")
                .uri("https://news.baidu.com/guonei")).build();
        routes.route("payment_route4", r -> r.path("/guoji")
                .uri("https://news.baidu.com/guoji")).build();
        return routes.build();
    }
}
