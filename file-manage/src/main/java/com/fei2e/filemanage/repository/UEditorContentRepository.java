package com.fei2e.filemanage.repository;

import com.fei2e.filemanage.entity.UEditorContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ClassName FileBase
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/15 9:56
 * @Version 1.0
 **/
public interface UEditorContentRepository extends JpaRepository<UEditorContent, Integer>, JpaSpecificationExecutor<UEditorContent> {

}
