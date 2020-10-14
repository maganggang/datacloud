package com.fei2e.filemanage.controller;

import com.fei2e.filemanage.entity.BaseResult;
import com.fei2e.filemanage.entity.FileBase;
import com.fei2e.filemanage.service.FileBaseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


/**
 * @ClassName FileBaseController
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/15 10:08
 * @Version 1.0
 **/
@RestController
public class FileBaseController {
    @Autowired
    private FileBaseBiz fileBaseBiz;
    /**
     * @Author mgg
     * @Description 上传附件并将数据导入表中
     * @Date 10:30 2020/9/24
     * @Param [file, request]
     * @return com.fei2e.filemanage.entity.BaseResult<com.fei2e.filemanage.entity.FileBase>
     **/
    @PostMapping("/upload")
    @ResponseBody
    public BaseResult<FileBase> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        return fileBaseBiz.uploadFile(file,request);
    }
    /**
     * 实现多文件上传
     * */
    @RequestMapping(value="/uploads",method= RequestMethod.POST)
    @ResponseBody
    public BaseResult<List<FileBase>> multifileUpload(@RequestParam("files")List<MultipartFile> files,HttpServletRequest request){
        return fileBaseBiz.uploadFiles(files,request);
    }
    /**
     * @Author mgg
     * @Description 文件下载
     * @Date 10:31 2020/9/24
     * @Param [id, response, request]
     * @return com.fei2e.filemanage.entity.BaseResult<java.lang.Boolean>
     **/
    @RequestMapping("download/{id}")
    public BaseResult<Boolean> downLoad(@PathVariable Integer id , HttpServletResponse response,HttpServletRequest request)throws UnsupportedEncodingException{
        return fileBaseBiz.downloadFile(id,response,request);
    }
    /**
     * @Author mgg
     * @Description 多文件打包下载
     * @Date 10:32 2020/9/24
     * @Param [response, ids]
     * @return void
     **/
    @RequestMapping(value = "/downloadZip", method = RequestMethod.GET)
    public void zipDownload(HttpServletResponse response,@RequestParam List<Integer> ids) {
        fileBaseBiz.downloadZipFile(ids, response);
    }
    /**
     * @Author mgg
     * @Description 文件修改
     * @Date 10:32 2020/9/24
     * @Param [id, file, request]
     * @return com.fei2e.filemanage.entity.BaseResult<com.fei2e.filemanage.entity.FileBase>
     **/
    //文件的修改
    @PostMapping("/update/{id}")
    @ResponseBody
    public BaseResult<FileBase> updateFile(@PathVariable Integer id ,@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        return fileBaseBiz.updateFile(id,file,request);
    }
    //文件的删除
    @RequestMapping("delete/{id}")
    public BaseResult<Boolean> deleteFile(@PathVariable Integer id){
        return fileBaseBiz.deleteFile(id);
    }
}
