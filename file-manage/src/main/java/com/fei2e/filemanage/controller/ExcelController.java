package com.fei2e.filemanage.controller;

import com.fei2e.filemanage.tools.ReadExcelTools;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName ExcelController
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/24 11:34
 * @Version 1.0
 **/

@Controller
@RequestMapping("excel")
public class ExcelController {
    @PostMapping("read")
    @ResponseBody
    public List<String[]>  readExcel(@RequestParam MultipartFile file) throws IOException {
            List<String[]>  string=  ReadExcelTools.readExcel(file);
          return string;
    }
}
