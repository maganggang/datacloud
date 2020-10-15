package com.fei2e.filemanage.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassName FileBaseRest
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/15 14:08
 * @Version 1.0
 **/
@Controller
public class FileBaseRest {
    @Autowired
    HttpServletRequest request;
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println(request.getHeader("Authorization"));
        return "hello world -- client two";
    }
}
