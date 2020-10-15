package com.fei2e.getlost.controller;

import com.fei2e.getlost.entity.Account;
import com.fei2e.getlost.entity.BaseResult;
import com.fei2e.getlost.entity.User;
import com.fei2e.getlost.service.AccountService;
import com.fei2e.getlost.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassName UserController
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/29 9:07
 * @Version 1.0
 **/
@Api(description = "用户管理")
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("register")
    public BaseResult<User> registe(HttpServletRequest request, @RequestBody User user){

        return userService.register(request,user);
    }
}
