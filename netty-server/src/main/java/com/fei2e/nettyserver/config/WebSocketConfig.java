package com.fei2e.nettyserver.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName WebSocketConfig
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/12/3 15:03
 * @Version 1.0
 **/
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "ws")
public class WebSocketConfig {
    private int port;
    private String host;
    private boolean ssl;
    private String cert;
    private String key;
}
