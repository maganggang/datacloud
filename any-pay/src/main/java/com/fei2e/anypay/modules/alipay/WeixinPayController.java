package com.fei2e.anypay.modules.alipay;

import com.fei2e.anypay.entity.Product;
import com.fei2e.anypay.service.IWeixinPayService;
import com.fei2e.anypay.util.ConfigUtil;
import com.fei2e.anypay.util.PayCommonUtil;
import com.fei2e.anypay.util.XMLUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @ClassName WeixinPayController
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/20 10:29
 * @Version 1.0
 **/
@Api(tags = "微信支付")
@Controller
@RequestMapping("WXpay")
public class WeixinPayController {
    @Autowired
    private IWeixinPayService weixinPayService;
    @ApiOperation(value="hello")
    @RequestMapping(value="hello",method= RequestMethod.GET)
    @ResponseBody
    public String  hello() {
        return "Hello";
    }
    @ApiOperation(value="二维码支付(模式一)根据商品ID预先生成二维码")
    @RequestMapping(value="qcPay1",method= RequestMethod.POST)
    @ResponseBody
    public String  qcPay1(@RequestBody Product product) {
        String map=weixinPayService.weixinPay1(product);
        return map;
    }
    /**
     * 模式一支付回调URL(生成二维码见 qrCodeUtil)
     * 商户支付回调URL设置指引：进入公众平台-->微信支付-->开发配置-->扫码支付-->修改
     * @Author  科帮网
     * @param request
     * @param response
     * @throws Exception  void
     * @Date	2017年8月3日
     * 更新日志
     * 2017年8月3日  科帮网 首次创建
     *
     */
    @ApiOperation(value="模式一支付回调URL")
    @RequestMapping(value="payBack",method=RequestMethod.POST)
    public void payBack(HttpServletRequest request, HttpServletResponse response) throws Exception {
         weixinPayService.payBack(request,response);
    }
    @ApiOperation(value="二维码支付(模式二)下单并生成二维码")
    @RequestMapping(value="qcPay2",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String>  qcPay2(@RequestBody Product product) {
        //参数自定义  这只是个Demo
        product.setSpbillCreateIp("192.168.0.180");
        Map<String, String> message  =  weixinPayService.weixinPay2(product);
        return message;
    }
    /**
     * 支付后台回调 支付模式二
     * @Author  科帮网
     * @param request
     * @param response
     * @throws Exception  void
     * @Date	2017年7月31日
     * 更新日志
     * 2017年7月31日  科帮网 首次创建
     *
     */
    @ApiOperation(value="支付后台回调")
    @RequestMapping(value="pay",method=RequestMethod.POST)
    public void wxNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        weixinPayService.wxNotify(request,response);
    }
}
