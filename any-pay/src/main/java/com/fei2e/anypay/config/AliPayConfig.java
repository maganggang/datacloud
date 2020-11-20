package com.fei2e.anypay.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName AliPayConfig
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/20 11:04
 * @Version 1.0
 **/
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "alipay")
public class AliPayConfig {
    private String appId;
    private String publicKey;
    private String privateKey;
    private String serverUrl;
    private String domain;
}
