package com.fei2e.anypay.service.impl;

import com.fei2e.anypay.config.WeiXinConfig;
import com.fei2e.anypay.entity.Product;
import com.fei2e.anypay.service.IWeixinPayService;
import com.fei2e.anypay.util.ConfigUtil;
import com.fei2e.anypay.util.HttpUtil;
import com.fei2e.anypay.util.PayCommonUtil;
import com.fei2e.anypay.util.XMLUtil;
import com.github.wxpay.sdk.WXPayConstants;
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
        //读取参数
        InputStream inputStream = request.getInputStream();
        StringBuffer sb = new StringBuffer();
        String s;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((s = in.readLine()) != null) {
            sb.append(s);
        }
        in.close();
        inputStream.close();

        //解析xml成map
        Map<String, String> map = XMLUtil.doXMLParse(sb.toString());
        //过滤空 设置 TreeMap
        SortedMap<Object, Object> packageParams = new TreeMap<>();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String parameter = (String) it.next();
            String parameterValue = map.get(parameter);

            String v = "";
            if (null != parameterValue) {
                v = parameterValue.trim();
            }
            packageParams.put(parameter, v);
        }
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
           // public final static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
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
}
