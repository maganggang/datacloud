package com.fei2e.getlost.controller;

import com.alibaba.fastjson.JSONObject;
import com.fei2e.getlost.entity.Account;
import com.fei2e.getlost.entity.BaseResult;
import com.fei2e.getlost.feign.FileFeignServer;
import com.fei2e.getlost.service.AccountService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName AccountController
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/27 13:40
 * @Version 1.0
 **/
@Api(description = "账号操作接口")
@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    private FileFeignServer fileFeignServer;
    @RequestMapping("/")
    public String home(){
        return  fileFeignServer.hello();
    }
    @Autowired
    private AccountService accountService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    //账号注册通过手机号进行注册，注册后密码加密，手机号或者邮箱进行更换密码
    @ApiOperation(value = "登录", notes="登录")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType ="String", paramType = "query", name = "userName", value = "用户名", required = true),
            @ApiImplicitParam(dataType ="String", paramType = "query", name = "password", value = "密码", required = true),
    })
    @GetMapping("login")
    public Account login(@RequestParam("userName") String userName,
                         @RequestParam("password") String password, HttpServletResponse response){
        //登录成功后清除所有该账号的session -1
        Account account=accountService.login(userName,password);
        account.setPassword(null);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("userId",account.getUserId());
        jsonObject.put("accountId",account.getAccountId());
        jsonObject.put("username",account.getUserName());
        String token=UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(token, jsonObject.toJSONString(),60*30, TimeUnit.SECONDS);
        account.setToken(token);
        response.setHeader("token",token);
        return account;
    }
    @ApiOperation(value = "注册", notes="申请账号")
    @ApiImplicitParam(name = "telephone", value = "电话号码", paramType = "body", required = true, dataType = "Account")
    @PostMapping("register")
    public BaseResult<Account> register(HttpServletRequest request, @RequestBody Account account){
        //注册成功后清除所有该账号的session -1
        return accountService.register(account);
    }
    @ApiOperation(value = "注销", notes="退出账号")
    @PutMapping("logout")
    public void logout(HttpServletRequest request){
        //登录成功后清除所有该账号的session -1
        HttpSession session = request.getSession();
        if(session.getAttribute("accountId")!=null){
            String accountId=session.getAttribute("accountId").toString();
            session.removeAttribute("loginUserId");
            session.removeAttribute("accountId");
            redisTemplate.opsForHash().delete("loginAccount:"+ accountId);
            session.invalidate();
        }

    }


}
