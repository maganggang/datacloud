package com.fei2e.filemanage.feign;

import com.fei2e.filemanage.entity.FileBase;
import com.fei2e.filemanage.service.FileBaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName FileBaseRest
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/15 14:08
 * @Version 1.0
 **/
@Controller
public class FileBaseRest {
    @Autowired
    HttpServletRequest request;
    @Autowired
    private FileBaseBiz fileBaseBiz;
    @PostMapping("api/update")
    @ResponseBody
    public int updateFileCount(@RequestBody List<Integer> ids){
        return  fileBaseBiz.updateFileCount(ids);
    }
    /**
     * @Author mgg
     * @Description 删除数据
     * @Date 15:53 2020/10/16
     * @Param [fileIds]
     * @return boolean
     **/
    @DeleteMapping("api/delete")
    @ResponseBody
    boolean removeFiles(@RequestBody List<Integer> fileIds){
        for (Integer id:fileIds) {
            fileBaseBiz.deleteFile(id);
        }
        return true;
    }
    @GetMapping("api/select")
    @ResponseBody
    List<FileBase> selectByIds(@RequestBody List<Integer> list){
        return  fileBaseBiz.selectByIds(list);
    }
}
