package xyz.izgnod.credit.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 服务A的启动类
 *
 * @author zhonghuashishan
 */
@SpringBootApplication
//@EnableEurekaClient
@MapperScan("xyz.izgnod.credit.mapper")
public class CreditServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditServiceApplication.class, args);
    }

}