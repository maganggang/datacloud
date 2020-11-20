package com.fei2e.anypay.service.impl;

import com.fei2e.anypay.config.WeiXinConfig;
import com.fei2e.anypay.entity.Product;
import com.fei2e.anypay.service.IWeixinPayService;
import com.fei2e.anypay.util.*;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import org.apache.commons.lang3.StringUtils;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @ClassName IWeixinPayServiceImpl
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/20 11:12
 * @Version 1.0
 **/
@Service
public class IWeixinPayServiceImpl implements IWeixinPayService {
    @Autowired
    private WeiXinConfig weiXinConfig;
    @Override
    public String weixinPay1(Product product) {
        //商户支付回调URL设置指引：进入公众平台-->微信支付-->开发配置-->扫码支付-->修改 加入回调URL
        //封装通用参数 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_4
        SortedMap<Object, Object> packageParams =ConfigUtil.commonParams(weiXinConfig);
        packageParams.put("product_id", product.getProductId());//真实商品ID
        packageParams.put("time_stamp", PayCommonUtil.getCurrTime());
        //生成签名
        String sign = PayCommonUtil.createSign("UTF-8", packageParams, weiXinConfig.getApiKey());
        //组装二维码信息(注意全角和半角：的区别 狗日的腾讯)
        StringBuffer qrCode = new StringBuffer();
        qrCode.append("weixin://wxpay/bizpayurl?");
        qrCode.append("appid="+weiXinConfig.getAppId());
        qrCode.append("&mch_id="+weiXinConfig.getMchId());
        qrCode.append("&nonce_str="+packageParams.get("nonce_str"));
        qrCode.append("&product_id="+product.getProductId());
        qrCode.append("&time_stamp="+packageParams.get("time_stamp"));
        qrCode.append("&sign="+sign);
        return qrCode.toString();
    }
/**
 * @Author mgg
 * @Description  模式一支付回调，暂时无法测试，需要商家后台绑定回调url
 * @Date 14:44 2020/11/20
 * @Param 
 * @return
 **/
    @Override
    public void payBack(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String requestStr=HttpUtil.initRequest(request);
        //解析xml成map
        Map<String, String> map = XMLUtil.doXMLParse(requestStr);
        Map<String,String> result=WXPayUtil.xmlToMap(requestStr);
        //过滤空 设置 TreeMap
        SortedMap<Object, Object> packageParams=ConfigUtil.MapToMap(result);
        //判断签名是否正确
        if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, weiXinConfig.getApiKey())) {
            //统一下单
            SortedMap<Object, Object> params=ConfigUtil.commonParams(weiXinConfig);
            //随即生成一个 入库 走业务逻辑
            String out_trade_no=Long.toString(System.currentTimeMillis());
            params.put("body", "模式一扫码支付");// 商品描述
            params.put("out_trade_no", out_trade_no);// 商户订单号
            params.put("total_fee", "100");// 总金额
            params.put("spbill_create_ip", "192.168.0.169");// 发起人IP地址
            params.put("notify_url", weiXinConfig.getNotifyUrl());// 回调地址
            params.put("trade_type", "NATIVE");// 交易类型

            String paramsSign = PayCommonUtil.createSign("UTF-8", params,  weiXinConfig.getApiKey());
            params.put("sign", paramsSign);// 签名
            String requestXML = PayCommonUtil.getRequestXml(params);
            // 微信支付统一接口(POST)
            String resXml = HttpUtil.postData(WXPayConstants.UNIFIEDORDER_URL, requestXML);
            Map<String, String>  payResult = XMLUtil.doXMLParse(resXml);
            String returnCode =  payResult.get("return_code");
            if("SUCCESS".equals(returnCode)){
                String resultCode = payResult.get("result_code");
                if("SUCCESS".equals(resultCode)){
                    String prepay_id = payResult.get("prepay_id");
                    SortedMap<Object, Object> prepayParams=ConfigUtil.commonParams(weiXinConfig);
                    prepayParams.put("prepay_id", prepay_id);
                    prepayParams.put("return_code", "SUCCESS");
                    prepayParams.put("result_code", "SUCCESS");
                    String prepaySign =  PayCommonUtil.createSign("UTF-8", prepayParams, weiXinConfig.getApiKey());
                    prepayParams.put("sign", prepaySign);
                    String prepayXml = PayCommonUtil.getRequestXml(prepayParams);

                    //通知微信 预下单成功
                    BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
                    out.write(prepayXml.getBytes());
                    out.flush();
                    out.close();
                }else{
                    String errCodeDes = map.get("err_code_des");
                }
            }else{
                String returnMsg = map.get("return_msg");
            }
        }else{
        }
    }
/**
 * @Author mgg
 * @Description  模式二支付
 * @Date 16:28 2020/11/20
 * @Param
 * @return
 **/
    @Override
    public Map<String, String> weixinPay2(Product product) {
        System.out.println("订单号：{}生成微信支付码"+product.getOutTradeNo());
            // 账号信息
            String trade_type = "NATIVE";// 交易类型 原生扫码支付
            SortedMap<Object, Object> packageParams=ConfigUtil.commonParams(weiXinConfig);
            packageParams.put("product_id", product.getProductId());// 商品ID
            packageParams.put("body", product.getBody());// 商品描述
            packageParams.put("out_trade_no", product.getOutTradeNo());// 商户订单号
            String totalFee = product.getTotalFee();
            totalFee =  CommonUtils.subZeroAndDot(totalFee);
            packageParams.put("total_fee", totalFee);// 总金额
            packageParams.put("spbill_create_ip", product.getSpbillCreateIp());// 发起人IP地址
            packageParams.put("notify_url", weiXinConfig.getNotifyUrl());// 回调地址
            packageParams.put("trade_type", trade_type);// 交易类型
            String sign = PayCommonUtil.createSign("UTF-8", packageParams, weiXinConfig.getApiKey());
            packageParams.put("sign", sign);// 签名

            String requestXML = PayCommonUtil.getRequestXml(packageParams);
            String resXml = HttpUtil.postData(WXPayConstants.UNIFIEDORDER_URL, requestXML);
            Map<String, String> map = null;
            try {
                map = WXPayUtil.xmlToMap(resXml);
            } catch (Exception e) {
                e.printStackTrace();
            }
         return map;
    }

    @Override
    public void wxNotify(HttpServletRequest request, HttpServletResponse response)throws Exception{
        String requestStr=HttpUtil.initRequest(request);
        if(requestStr!=null&& StringUtils.isNotEmpty(requestStr)){
            Map<String,String> result=WXPayUtil.xmlToMap(requestStr);
            //过滤空 设置 TreeMap
            SortedMap<Object, Object> packageParams=ConfigUtil.MapToMap(result);
            // 判断签名是否正确
            if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, weiXinConfig.getApiKey())) {
                // ------------------------------
                // 处理业务开始
                // ------------------------------
                String resXml = "";
                if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
                    // 这里是支付成功
                    //这里 根据实际业务场景 做相应的操作
                    // 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
                    resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                } else {
                    resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                }
                // ------------------------------
                // 处理业务完毕
                // ------------------------------
                BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
                out.write(resXml.getBytes());
                out.flush();
                out.close();
            }
        }

    }
}
