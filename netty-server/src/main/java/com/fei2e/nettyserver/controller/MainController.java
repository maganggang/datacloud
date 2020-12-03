package com.fei2e.nettyserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MainController
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/12/3 17:47
 * @Version 1.0
 **/
@RestController
public class MainController {
    @Value("${ws.port}")
    private int port;
    @RequestMapping("/hello")
    public String hello(){
        return port+"";
    }
}
