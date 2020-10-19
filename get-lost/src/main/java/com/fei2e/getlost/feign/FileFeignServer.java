package com.fei2e.getlost.feign;

import com.fei2e.demo.entity.FileBase;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName FileBaseFeign
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/15 16:18
 * @Version 1.0
 **/
@Service
@FeignClient(name = "file-manage",fallback = FileError.class)
public interface FileFeignServer {
    @RequestMapping("/hello")
     String hello();
    @PostMapping("api/update")
    @ResponseBody
    int updateFileCount(@RequestBody List<Integer> ids);
    @DeleteMapping("api/delete")
    @ResponseBody
    boolean removeFiles(@RequestBody List<Integer> fileIds);
    @PostMapping("api/select")
    @ResponseBody
    List<FileBase> selectByIds(@RequestBody List<Integer> list);
}
