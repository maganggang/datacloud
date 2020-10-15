package com.fei2e.getlost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.fei2e.getlost.mapper")
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class GetLostApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetLostApplication.class, args);
    }

}
