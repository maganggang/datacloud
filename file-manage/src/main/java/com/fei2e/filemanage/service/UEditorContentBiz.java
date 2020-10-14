package com.fei2e.filemanage.service;

import com.fei2e.filemanage.entity.BaseResult;
import com.fei2e.filemanage.entity.FileUEditor;
import com.fei2e.filemanage.entity.UEditorContent;
import com.fei2e.filemanage.repository.FileBaseRepository;
import com.fei2e.filemanage.repository.FileUEditorRepository;
import com.fei2e.filemanage.repository.UEditorContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName UEditorContentBiz
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/16 15:04
 * @Version 1.0
 **/
@Service
public class UEditorContentBiz {
    @Autowired
    private UEditorContentRepository uEditorContentRepository;
    @Autowired
    private FileBaseRepository fileBaseRepository;
    @Autowired
    private FileUEditorRepository fileUEditorRepository;
    public BaseResult<UEditorContent> saveContent(UEditorContent uEditorContent) {
        BaseResult<UEditorContent> result=new BaseResult<>();
        uEditorContent.setCreateTime(new Date());
        uEditorContent.setStatus(0);
        uEditorContentRepository.save(uEditorContent);
        result.setData(uEditorContent);
        //绑定附件
        //通过code找附件ID
        List<String> codes=uEditorContent.getFileList();
        if(codes!=null&&codes.size()>0){
            codes=checkCodes(codes,uEditorContent.getContent());
            List<Integer> fileBases=fileBaseRepository.findByCode(codes);
            if(fileBases!=null&&fileBases.size()>0){
                for (Integer id:fileBases){
                    FileUEditor fileUEditor=new FileUEditor();
                    fileUEditor.setFileId(id);
                    fileUEditor.setCreateTime(new Date());
                    fileUEditor.setUeditorId(uEditorContent.getId());
                    fileUEditorRepository.save(fileUEditor);
                }
                //使用状态更改
                fileBaseRepository.updateCount(fileBases);
            }

        }
        return result;
    }

    private List<String> checkCodes(List<String> codes, String content) {
        List<String> stringList=new ArrayList<>();
        for (String s:codes){
            int result1 = content.indexOf(s);
            if(result1 != -1){
                stringList.add(s);
            }
        }
        return stringList;
    }

    public String getById(Integer id) {
        UEditorContent uEditorContent=uEditorContentRepository.getOne(id);
        if(uEditorContent.getContent()!=null){
            return uEditorContent.getContent();
        }
        return "没有相关内容";
    }
}
