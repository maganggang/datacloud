package com.fei2e.filemanage.controller;

import com.alibaba.fastjson.JSONObject;
import com.fei2e.filemanage.config.UEditorConfig;
import com.fei2e.filemanage.entity.BaseResult;
import com.fei2e.filemanage.entity.UEditorContent;
import com.fei2e.filemanage.service.FileBaseBiz;
import com.fei2e.filemanage.service.UEditorContentBiz;
import com.fei2e.filemanage.ueditor.ActionEnter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

/**
 * @ClassName UEditorController
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/14 14:25
 * @Version 1.0
 **/
@Controller
public class UEditorController {
    @Value("${web.upload-path}")
    private String BASE_PATH;
    @Autowired
    private UEditorConfig editorConfig;
    @Autowired
    private UEditorContentBiz uEditorContentBiz;
    @Autowired
    private FileBaseBiz fileBaseBiz;
    @RequestMapping("/")
    private String showPage(){
        return "index";
    }

    @RequestMapping(value="/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        System.out.println(editorConfig.getBasePath());
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            System.out.println(exec);
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
            JSONObject jsonObject=JSONObject.parseObject(exec);
            //该接口不仅仅附件上传
            if(jsonObject.getLong("size")!=null){
                fileBaseBiz.saveFileData(jsonObject.getString("original"),request.getHeader("user-agent"),request.getRemoteHost(),
                        Calendar.getInstance(),jsonObject.getLong("size"),jsonObject.getString("type").replace(".",""),
                        BASE_PATH+jsonObject.getString("url"),jsonObject.getString("title"),null);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @PostMapping("ueditor/save")
    @ResponseBody
    public BaseResult<UEditorContent> saveContent(@RequestBody UEditorContent uEditorContent){
        return uEditorContentBiz.saveContent(uEditorContent);
    }

    @RequestMapping("view/{id}")
    public String  getView(@PathVariable Integer id, Model model) {
        String content=uEditorContentBiz.getById(id);
        model.addAttribute("content",content);
        return "view";
    }
    @RequestMapping("html/{id}")
    @ResponseBody
    public String  getContent(@PathVariable Integer id) {
        String content=uEditorContentBiz.getById(id);
        return content;
    }
}
