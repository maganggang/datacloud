package com.fei2e.anypay.modules.alipay;

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
}
