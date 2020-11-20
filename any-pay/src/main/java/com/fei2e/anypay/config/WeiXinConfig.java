package com.fei2e.anypay.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName WeiXinConfig
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/20 10:53
 * @Version 1.0
 **/
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "weixin")
public class WeiXinConfig {
    private String appId;
    private String mchId;
    private String apiKey;
    private String signType;
    private String certPath;
    @Value("${weixin.notify_url}")
    private String notifyUrl;

}
