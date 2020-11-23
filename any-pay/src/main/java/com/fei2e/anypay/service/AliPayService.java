package com.fei2e.anypay.service;

import com.fei2e.anypay.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName AliPayService
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/23 10:00
 * @Version 1.0
 * 支付宝
 * 创建者 科帮网
 * 创建时间	2018年1月15日
 * ======================
 * 商户端私钥：
 * 由我们自己生成的RSA私钥（必须与商户端公钥是一对），生成后要保存在服务端，绝对不能保存在客户端，也绝对不能从服务端下发。
 * 用来对订单信息进行加签，加签过程一定要在服务端完成，绝对不能在客户端做加签工作，客户端只负责用加签后的订单信息调起支付宝来支付。
 * ======================
 * 商户端公钥：
 * 由我们自己生成的RSA公钥（必须与商户端私钥是一对），生成后需要填写在支付宝开放平台，
 * 用来给支付宝服务端验签经过我们加签后的订单信息，以确保订单信息确实是我们商户端发给支付宝的，并且确保订单信息在传输过程中未被篡改。
 * ======================
 * 支付宝私钥：
 * 支付宝自己生成的，他们自己保存，开发者是无法看到的，用来对支付结果进行加签。
 * ======================
 * 支付宝公钥：
 * 支付宝公钥和支付宝私钥是一对，也是支付宝生成的，当我们把商户端公钥填写在支付宝开放平台后，平台就会给我们生成一个支付宝公钥。
 * 我们可以复制下来保存在服务端，同样不要保存在客户端，并且不要下发，避免被反编译或截获，而被篡改支付结果。
 * 用来让服务端对支付宝服务端返给我们的同步或异步支付结果进行验签，以确保支付结果确实是由支付宝服务端返给我们服务端的，而且没有被篡改。
 * 对支付结果的验签工作也一定要在服务端完成，绝对不能在客户端验签，因为支付宝公钥一旦存储在客户端用来验签，那就可能被反编译，这样就谁都可以验签支付结果并篡改了。
 * ======================
 * 支付宝建议加签方式升级为RSA(SHA256)密钥，以为 SHA 貌似已经被破解了。
 **/
public interface AliPayService {
    String aliPayPc(Product product);

    String aliPayMobile(Product product);

    String aliPay(Product product);

    String appPay(Product product);

    void payBack(HttpServletRequest request, HttpServletResponse response) throws IOException;

    String payFront(HttpServletRequest request);
}
