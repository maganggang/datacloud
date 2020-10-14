package com.fei2e.getlost.service.impl;

import com.fei2e.getlost.base.BaseMapper;
import com.fei2e.getlost.base.BaseServiceImpl;
import com.fei2e.getlost.entity.Account;
import com.fei2e.getlost.entity.BaseResult;
import com.fei2e.getlost.entity.User;
import com.fei2e.getlost.mapper.UserMapper;
import com.fei2e.getlost.service.AccountService;
import com.fei2e.getlost.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @ClassName UserServiceImpl
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/29 9:09
 * @Version 1.0
 **/
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    protected BaseMapper<User> getMapper() {
        return userMapper;
    }
    @Autowired
    private AccountService accountService;
    @Override
    public BaseResult<User> register(HttpServletRequest request, User user) {
        BaseResult<User> result=new BaseResult<>();
        HttpSession session = request.getSession();
        Integer accountId=Integer.parseInt(session.getAttribute("accountId").toString()) ;
        user.setCreateTime(new Date());
        user.setCreatorId(accountId);
        userMapper.insert(user);
        Account account=new Account();
        account.setAccountId(accountId);
        account.setUserId(user.getUserId());
        accountService.updateByPrimaryKeySelective(account);
        result.setData(user);
        return result;
    }
}
