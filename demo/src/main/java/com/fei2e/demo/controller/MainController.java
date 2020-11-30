package com.fei2e.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName MainController
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/30 13:47
 * @Version 1.0
 **/
@Controller
public class MainController {
    @ResponseBody
    @RequestMapping("/GetEndPoints")
    public String GetAllPoint(HttpServletRequest request){
        String path = request.getContextPath();
        String host = request.getServerName();
        String endPointPath = "/actuator";
        StringBuilder sb = new StringBuilder();

        sb.append("<h2>Sprig Boot Actuator</h2>");
        sb.append("<ul>");
        String url = "http://" + host + ":8004" + path + endPointPath;
        sb.append("<li><a href='" + url + "'>" + url + "</a></li>");
        sb.append("</ul>");
        return sb.toString();
    }
}
