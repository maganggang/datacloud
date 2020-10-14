package com.fei2e.getlost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.fei2e.getlost.mapper")
@SpringBootApplication
public class GetLostApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetLostApplication.class, args);
    }

}
