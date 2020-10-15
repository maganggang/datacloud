package com.fei2e.getlost.filter;

import com.alibaba.fastjson.JSONObject;
import com.fei2e.getlost.entity.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisSessionInterceptor
 * @DescripTion 拦截登录失效的请求
 * @Author dell
 * @Date 2020/9/28 17:43
 * @Version 1.0
 **/
@Component
public class RedisSessionInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String token = request.getHeader("Authorization");
        if(token!=null&&!StringUtils.isEmpty(token)){
            String json=redisTemplate.opsForValue().get(token);//根据key获取缓存中的val
            if(json!=null&& !StringUtils.isEmpty(json)){
                JSONObject jsonObject=JSONObject.parseObject(json);
                HttpSession session = request.getSession();
                for(String str:jsonObject.keySet()){
                    //无论访问的地址是不是正确的，都进行登录验证，
                    // 登录成功后的访问再进行分发，404的访问自然会进入到错误控制器中
                    session.setAttribute(str,jsonObject.get(str));
                }
                if (session.getAttribute("accountId") != null) {
                    //向redis里存入数据和设置缓存时间
                    redisTemplate.opsForValue().set(token, jsonObject.toJSONString(),60*30, TimeUnit.SECONDS);
                    return true;
                }

            }
        }
        response401(response);
        return false;
    }

    private void response401(HttpServletResponse response)
    {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try{
            response.getWriter().print(JSONObject.toJSONString(new BaseResult<String>(500, "", "用户未登录！")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {

    }

}
