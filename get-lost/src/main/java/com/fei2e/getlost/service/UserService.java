package com.fei2e.getlost.service;

import com.fei2e.getlost.base.BaseService;
import com.fei2e.getlost.entity.BaseResult;
import com.fei2e.getlost.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService extends BaseService<User> {
    BaseResult<User> register(HttpServletRequest request, User user);
}
