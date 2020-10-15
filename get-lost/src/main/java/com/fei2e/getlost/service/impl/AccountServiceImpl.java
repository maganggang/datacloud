package com.fei2e.getlost.service.impl;

import com.fei2e.getlost.base.BaseMapper;
import com.fei2e.getlost.base.BaseServiceImpl;
import com.fei2e.getlost.entity.Account;
import com.fei2e.getlost.entity.BaseResult;
import com.fei2e.getlost.mapper.AccountMapper;
import com.fei2e.getlost.service.AccountService;
import com.fei2e.getlost.util.StringCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @ClassName AccountServiceImpl
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/27 13:37
 * @Version 1.0
 **/
@Service
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Override
    protected BaseMapper<Account> getMapper() {
        return accountMapper;
    }

    @Override
    public Account login(String userName, String password) {
        return accountMapper.getAccountByNameWord(userName,password);
    }

    @Override
    public BaseResult<Account> register(Account account) {
        BaseResult<Account> result=new BaseResult<>();
        if(account.getEmail()!=null&&!StringUtils.isEmpty(account.getEmail())){
            if(!StringCheckUtil.isEmail(account.getEmail())){
               result.setStatus(500);
               return result;
            }
        }
        if(account.getTelephone()!=null&&!StringUtils.isEmpty(account.getTelephone())){
            if(!StringCheckUtil.isPhone(account.getTelephone())){
                result.setStatus(500);
                return result;
            }
        }
        account.setCreateTime(new Date());
        int i=accountMapper.insert(account);
        if(i>0){
            result.setData(account);
        }else {
            result.setStatus(500);
            result.setMessage("注册失败");
        }
        return result;
    }
}
