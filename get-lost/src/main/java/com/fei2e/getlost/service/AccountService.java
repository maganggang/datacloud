package com.fei2e.getlost.service;

import com.fei2e.getlost.base.BaseService;
import com.fei2e.getlost.entity.Account;
import com.fei2e.getlost.entity.BaseResult;

/**
 * @ClassName AccountService
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/27 12:19
 * @Version 1.0
 **/
public interface AccountService extends BaseService<Account> {

    Account login(String userName, String password);

    BaseResult<Account> register(Account account);
}
