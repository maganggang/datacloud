package com.fei2e.demo.handler;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName BuildInfoActuator
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/30 14:26
 * @Version 1.0
 **/
@Component
public class BuildInfoActuator implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        Map<String,String> data=new HashMap<>();
        data.put("build.version","1.0.0");
        builder.withDetail("buildInfo",data);
    }
}
