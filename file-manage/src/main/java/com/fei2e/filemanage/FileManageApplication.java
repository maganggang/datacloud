package com.fei2e.filemanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(basePackages = {"com.fei2e.filemanage.mapper"})
@SpringBootApplication
public class FileManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileManageApplication.class, args);
    }

}
