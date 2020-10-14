package com.fei2e.filemanage.config;

import com.fei2e.filemanage.filter.FileHeaderCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName MyfWebAppConfiguration
 * @DescripTion 拦截器
 * @Author dell
 * @Date 2020/9/15 17:52
 * @Version 1.0
 **/
@Configuration
public class MyfWebAppConfiguration extends WebMvcConfigurerAdapter {

    //拦截器，拦截文件流 this master
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new FileHeaderCheckInterceptor())
                .addPathPatterns("/**");
    }

}
