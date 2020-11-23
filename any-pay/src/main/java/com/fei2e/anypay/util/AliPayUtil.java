package com.fei2e.anypay.util;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.fei2e.anypay.config.AliPayConfig;

/**
 * @ClassName AliPayUtil
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/23 11:55
 * @Version 1.0
 **/
public class AliPayUtil {
    /**
     * 私有的默认构造子，保证外界无法直接实例化
     */
    private AliPayUtil(){};
    /**
     * 签名方式
     */
    public static String SIGN_TYPE = "RSA2";
    /**
     * 参数类型
     */
    public static String PARAM_TYPE = "json";
    /**
     * 编码
     */
    public static String CHARSET = "utf-8";

    public static AlipayClient initAliPay(AliPayConfig aliPayConfig){
        AlipayClient alipayClient = new DefaultAlipayClient(
                aliPayConfig.getServerUrl(), aliPayConfig.getAppId(),
                aliPayConfig.getPrivateKey(), PARAM_TYPE, CHARSET,
                aliPayConfig.getPublicKey(),SIGN_TYPE);
        return alipayClient;
    }
    public static AlipayTradeService initTradeService(AliPayConfig aliPayConfig){
        AlipayTradeService tradeService = new AlipayTradeServiceImpl.ClientBuilder()
                .setPrivateKey(aliPayConfig.getPrivateKey())
                .setAlipayPublicKey(aliPayConfig.getPublicKey())
                .setSignType(SIGN_TYPE)
                .setAppid(aliPayConfig.getAppId())
                .setGatewayUrl(aliPayConfig.getServerUrl())
                .build();
        return tradeService;
    }
}
