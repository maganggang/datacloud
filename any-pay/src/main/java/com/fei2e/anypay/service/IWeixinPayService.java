package com.fei2e.anypay.service;

import com.fei2e.anypay.entity.Product;
import org.jdom.JDOMException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @ClassName IWeixinPayService
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/20 11:10
 * @Version 1.0
 **/
public interface IWeixinPayService {
    String weixinPay1(Product product);

    void payBack(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
