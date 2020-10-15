package com.fei2e.getlost.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName FileBaseFeign
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/15 16:18
 * @Version 1.0
 **/
@FeignClient(name = "file-manage")
public interface FileFeignServer {
    @RequestMapping("/hello")
    public String hello();
}
