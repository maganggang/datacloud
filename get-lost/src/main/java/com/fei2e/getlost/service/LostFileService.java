package com.fei2e.getlost.service;

import com.fei2e.getlost.entity.LostFile;

import java.util.List;

/**
 * @ClassName LostFileService
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/15 11:53
 * @Version 1.0
 **/
public interface LostFileService {
     void insertList(Integer lostId, List<LostFile> lostFiles);
}
