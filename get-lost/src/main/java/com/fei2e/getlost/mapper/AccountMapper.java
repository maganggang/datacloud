package com.fei2e.getlost.mapper;

import com.fei2e.getlost.base.BaseMapper;
import com.fei2e.getlost.entity.Account;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import javax.websocket.server.PathParam;
@Repository
public interface AccountMapper extends BaseMapper<Account> {
     Account getAccountById(Integer id);
     Account getAccountByNameWord(@Param("userName") String userName,@PathParam("password") String password);
     Account getAccountByName(String userName);
}