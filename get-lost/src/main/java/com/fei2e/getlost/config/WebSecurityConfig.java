package com.fei2e.getlost.config;

import com.fei2e.getlost.filter.RedisSessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebSecurityConfig
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/28 17:48
 * @Version 1.0
 **/
@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {
    @Autowired
    private RedisSessionInterceptor redisSessionInterceptor;

    /**
     * addPathPatterns 添加拦截规则
     * excludePathPatterns 排除拦截规则
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        //所有已api开头的访问都要进入RedisSessionInterceptor拦截器进行登录验证，并排除login接口(全路径)。必须写成链式，分别设置的话会创建多个拦截器。
        registry.addInterceptor(redisSessionInterceptor).addPathPatterns("/**").excludePathPatterns("/account/**");
    }

}
