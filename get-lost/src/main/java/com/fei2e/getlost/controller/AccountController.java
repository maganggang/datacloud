package com.fei2e.getlost.controller;

import com.fei2e.getlost.entity.Account;
import com.fei2e.getlost.entity.BaseResult;
import com.fei2e.getlost.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassName AccountController
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/27 13:40
 * @Version 1.0
 **/
@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    //账号注册通过手机号进行注册，注册后密码加密，手机号或者邮箱进行更换密码
    @GetMapping("login")
    public Account login(HttpServletRequest request, @RequestParam("userName") String userName,@RequestParam("password") String password){
        //登录成功后清除所有该账号的session -1
        Account account=accountService.login(userName,password);
        account.setPassword(null);
        HttpSession session = request.getSession();
        session.setAttribute("loginUserId", account.getUserId());
        session.setAttribute("accountId", account.getAccountId());
        redisTemplate.opsForValue().set("loginAccount:" + account.getAccountId(), session.getId());
        return account;
    }
    @PostMapping("register")
    public BaseResult<Account> registe(HttpServletRequest request, @RequestBody Account account){
        //注册成功后清除所有该账号的session -1
        return accountService.register(account);
    }



}
