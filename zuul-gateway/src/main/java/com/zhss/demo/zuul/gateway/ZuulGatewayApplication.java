package com.zhss.demo.zuul.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZuulGatewayApplication {

    @LoadBalanced
    public static void main(String[] args) {
        SpringApplication.run(ZuulGatewayApplication.class, args);
    }

}