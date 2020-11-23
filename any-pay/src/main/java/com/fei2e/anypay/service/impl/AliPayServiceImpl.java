package com.fei2e.anypay.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

    @Override
    public String appPay(Product product) {
        String orderString ="";
        // 实例化客户端
        AlipayClient alipayClient = AliPayUtil.initAliPay(aliPayConfig);
        // 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        // SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(product.getBody());
        model.setSubject(product.getSubject());
        model.setOutTradeNo(product.getOutTradeNo());
        model.setTimeoutExpress("30m");
        model.setTotalAmount(product.getTotalFee());
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        try {
            // 这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient
                    .sdkExecute(request);
            orderString  = response.getBody();//就是orderString 可以直接给客户端请求，无需再做处理。
            //System.out.println(response.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return orderString ;
    }

    @Override
    public void payBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String  message = "success";
        Map<String, String> params = new HashMap<>();
        // 取出所有参数是为了验证签名
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            params.put(parameterName, request.getParameter(parameterName));
        }
        //验证签名 校验签名
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, Configs.getAlipayPublicKey(), AliPayUtil.CHARSET, AliPayUtil.SIGN_TYPE);
            //各位同学这里可能需要注意一下,2018/01/26 以后新建应用只支持RSA2签名方式，目前已使用RSA签名方式的应用仍然可以正常调用接口，注意下自己生成密钥的签名算法
            //signVerified = AlipaySignature.rsaCheckV1(params, Configs.getAlipayPublicKey(), "UTF-8","RSA2");
            //有些同学通过 可能使用了这个API导致验签失败，特此说明一下
            //signVerified = AlipaySignature.rsaCheckV2(params, Configs.getAlipayPublicKey(), "UTF-8");//正式环境
        } catch (AlipayApiException e) {
            e.printStackTrace();
            message =  "failed";
        }
        if (signVerified) {
           // logger.info("支付宝验证签名成功！");
            // 若参数中的appid和填入的appid不相同，则为异常通知
            if (!Configs.getAppid().equals(params.get("app_id"))) {
               // logger.info("与付款时的appid不同，此为异常通知，应忽略！");
                message =  "failed";
            }else{
                String outtradeno = params.get("out_trade_no");
                //在数据库中查找订单号对应的订单，并将其金额与数据库中的金额对比，若对不上，也为异常通知
                String status = params.get("trade_status");

                if (status.equals("WAIT_BUYER_PAY")) { // 如果状态是正在等待用户付款
                    //logger.info(outtradeno + "订单的状态正在等待用户付款");
                } else if (status.equals("TRADE_CLOSED")) { // 如果状态是未付款交易超时关闭，或支付完成后全额退款
                   // logger.info(outtradeno + "订单的状态已经关闭");
                } else if (status.equals("TRADE_SUCCESS") || status.equals("TRADE_FINISHED")) { // 如果状态是已经支付成功
                    //logger.info("(支付宝订单号:"+outtradeno+"付款成功)");
                    //这里 根据实际业务场景 做相应的操作
                } else {

                }
            }
        } else { // 如果验证签名没有通过
            message =  "failed";
           // logger.info("验证签名失败！");
        }
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(message.getBytes());
        out.flush();
        out.close();
    }

    @Override
    public String payFront(HttpServletRequest request) {
        try {
            //获取支付宝GET过来反馈信息
            Map<String,String> params = new HashMap<>();
            Map<String,String[]> requestParams = request.getParameterMap();
            for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }
            //商户订单号
            String orderNo = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //前台回调验证签名 v1 or v2
            boolean signVerified = rsaCheckV1(params);
            if(signVerified) {
               // logger.info("订单号"+orderNo+"验证签名结果[成功].");
                //处理业务逻辑
            }else {
              //  logger.info("订单号"+orderNo+"验证签名结果[失败].");
            }
        } catch (Exception e) {
            e.printStackTrace();
            //处理异常信息
        }
        //支付成功、跳转到成功页面
        return "success";
    }
    private boolean rsaCheckV2(Map<String, String> params) {
        //验证签名 校验签名
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV2(params, Configs.getAlipayPublicKey(), "UTF-8");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return signVerified;
    }
    private boolean rsaCheckV1(Map<String, String> params) {
        //验证签名 校验签名
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, Configs.getAlipayPublicKey(), "UTF-8");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return signVerified;
    }
}
