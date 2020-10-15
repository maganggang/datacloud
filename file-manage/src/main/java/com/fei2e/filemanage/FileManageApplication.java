package com.fei2e.filemanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan(basePackages = {"com.fei2e.filemanage.mapper"})
@SpringBootApplication
@EnableDiscoveryClient
public class FileManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileManageApplication.class, args);
    }

}
