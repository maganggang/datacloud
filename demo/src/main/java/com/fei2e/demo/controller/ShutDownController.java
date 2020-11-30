package com.fei2e.demo.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName ShutDownController
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/30 14:29
 * @Version 1.0
 **/
@Controller
public class ShutDownController {
    @ResponseBody
    @RequestMapping("/shutdown")
    public String actuatorSHutdown(){
        String url="http://localhost:8004/actuator/shutdown";
        HttpHeaders headers=new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate=new RestTemplate();
        HttpEntity<String> requestBody=new HttpEntity<>("",headers);
        String e=restTemplate.postForObject(url,requestBody,String.class);
        return "Result:"+e;
    }
}
