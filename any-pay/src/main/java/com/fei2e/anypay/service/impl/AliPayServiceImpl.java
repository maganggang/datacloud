package com.fei2e.anypay.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayResponse;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.utils.ZxingUtils;
import com.fei2e.anypay.config.AliPayConfig;
import com.fei2e.anypay.entity.Product;
import com.fei2e.anypay.service.AliPayService;
import com.fei2e.anypay.util.AliPayUtil;
import com.fei2e.anypay.util.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName AliPayServiceImpl
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/23 10:01
 * @Version 1.0
 **/
@Service
public class AliPayServiceImpl implements AliPayService {
    @Autowired
    private AliPayConfig aliPayConfig;
    private AlipayTradePagePayRequest aliPay(Product product,String way){
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(product.getFrontUrl());//前台通知
        alipayRequest.setNotifyUrl(aliPayConfig.getNotifyUrl());//后台回调
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", product.getOutTradeNo());
        bizContent.put("total_amount", product.getTotalFee());//订单金额:元
        bizContent.put("subject",product.getSubject());//订单标题
        bizContent.put("seller_id", "11010");//实际收款账号，一般填写商户PID即可
        bizContent.put("product_code", way);//电脑网站支付
        bizContent.put("body", product.getAttach());
        /**
         * 这里有三种模式可供选择
         * 如果在系统内支付，并且是弹出层支付，建议选择模式二、其他模式会跳出当前iframe(亲测有效)
         */
        bizContent.put("qr_pay_mode", "2");
        String biz = bizContent.toString().replaceAll("\"", "'");
        alipayRequest.setBizContent(biz);
        return alipayRequest;
    }
    /**
     * 支付宝PC支付下单
     * @param product
     * @return
     */
    @Override
    public String aliPayPc(Product product) {
        AlipayTradePagePayRequest alipayRequest = aliPay(product,"FAST_INSTANT_TRADE_PAY");
        String form ="";
        try {
            form = AliPayUtil.initAliPay(aliPayConfig).pageExecute(alipayRequest).getBody();
        } catch (Exception e) {
            System.out.println("支付宝构造表单失败");
            e.printStackTrace();
        }
        return form;
    }

    /**
     * 支付宝手机支付下单
     * @param product
     * @return
     */
    @Override
    public String aliPayMobile(Product product) {
        //手机网页支付
        AlipayTradePagePayRequest alipayRequest = aliPay(product,"QUICK_WAP_PAY");
        String form = "";
        try {
            form = AliPayUtil.initAliPay(aliPayConfig).pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            System.out.println("支付宝构造表单失败");
            e.printStackTrace();
        }
        return form;
    }

    /**
     * 订单号：{}生成支付宝支付码
     * @param product
     * @return
     */
    @Override
    public String aliPay(Product product) {
        String  message = "";
        //二维码存放路径
        String outTradeNo = product.getOutTradeNo();
        String subject = product.getSubject();
        String totalAmount =  CommonUtils.divide(product.getTotalFee(), "100").toString();
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        String sellerId = "";
        // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        String storeId = "test_store_id";
        // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2098100200300400500");
        // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
        String body = product.getBody();
        // 支付超时，定义为120分钟
        String timeoutExpress = "120m";
        // 创建扫码支付请求builder，设置请求参数
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                .setSubject(subject)
                .setTotalAmount(totalAmount)
                .setOutTradeNo(outTradeNo)
                .setSellerId(sellerId)
                .setBody(body)//128长度 --附加信息
                .setStoreId(storeId)
                .setExtendParams(extendParams)
                .setTimeoutExpress(timeoutExpress)
                .setNotifyUrl(aliPayConfig.getServerUrl());//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置

        AlipayF2FPrecreateResult result = AliPayUtil.initTradeService(aliPayConfig).tradePrecreate(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
               // logger.info("支付宝预下单成功: )");

                AlipayTradePrecreateResponse response = result.getResponse();
                JSONObject jsonObject=(JSONObject)JSONObject.toJSON(response);
                System.out.println(jsonObject.toJSONString());
                message=response.getQrCode();
                break;

            case FAILED:
              //  logger.info("支付宝预下单失败!!!");
                message ="";
                break;

            case UNKNOWN:
               // logger.info("系统异常，预下单状态未知!!!");
                message ="";
                break;

            default:
                message ="";
                break;
        }
        return message;
    }
}
