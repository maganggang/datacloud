package com.fei2e.filemanage.repository;

import com.fei2e.filemanage.entity.FileUEditor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ClassName FileUEditorRepository
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/18 9:07
 * @Version 1.0
 **/
public interface FileUEditorRepository extends JpaRepository<FileUEditor, Integer>, JpaSpecificationExecutor<FileUEditor> {
}
