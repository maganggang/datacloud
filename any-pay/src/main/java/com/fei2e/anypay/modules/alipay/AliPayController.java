package com.fei2e.anypay.modules.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.demo.trade.config.Configs;
import com.fei2e.anypay.entity.Product;
import com.fei2e.anypay.service.AliPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName AliPayController
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/23 10:00
 * @Version 1.0
 **/
@Api(tags ="支付宝支付")
@Controller
@RequestMapping(value = "alipay")
public class AliPayController {
    @Autowired
    private AliPayService aliPayService;
    @ApiOperation(value="电脑支付")
    @RequestMapping(value="pcPay",method= RequestMethod.POST)
    public String pcPay(@RequestBody Product product, ModelMap map) {
        String form  =  aliPayService.aliPayPc(product);
        map.addAttribute("form", form);
        return "index";
    }
    @ApiOperation(value="手机H5支付")
    @RequestMapping(value="mobilePay",method=RequestMethod.POST)
    public String  mobilePay(@RequestBody Product product,ModelMap map) {
        String form  =  aliPayService.aliPayMobile(product);
        map.addAttribute("form", form);
        return "index";
    }
    @ResponseBody
    @ApiOperation(value="二维码支付")
    @RequestMapping(value="qcPay",method=RequestMethod.POST)
    public String  qcPay(@RequestBody Product product) {
        String message  =  aliPayService.aliPay(product);
        return message;
    }
    @ResponseBody
    @ApiOperation(value="app支付服务端")
    @RequestMapping(value="appPay",method=RequestMethod.POST)
    public String  appPay(Product product) {
        String orderString  =  aliPayService.appPay(product);
        return orderString;
    }
    /**
     * 支付宝支付后台回调(二维码、H5、网站)
     * @Author  科帮网
     * @param request
     * @param response
     * @throws Exception  void
     * @Date	2017年7月30日
     * 更新日志
     * 2017年7月30日  科帮网 首次创建
     */
    @ApiOperation(value="支付宝支付回调(二维码、H5、网站)")
    @RequestMapping(value="payBack",method=RequestMethod.POST)
    public void alipay_notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        aliPayService.payBack(request,response);
    }
    /**
     * 支付宝支付PC端前台回调
     * @Author  科帮网
     * @param request
     * @return  String
     * @Date	2018年11月20日
     * 更新日志
     * 2018年11月20日  科帮网 首次创建
     */
    @RequestMapping(value="/frontRcvResponse",method=RequestMethod.POST)
    public String  frontRcvResponse(HttpServletRequest request){
        return aliPayService.payFront(request);
    }
}
