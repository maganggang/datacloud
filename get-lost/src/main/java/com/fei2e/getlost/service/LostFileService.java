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
     List<LostFile> selectByLostId(Integer id);

    void insertList(Integer lostId, List<LostFile> lostFiles);

    int deleteFileByLostId(Integer id);

    List<Integer> selectFileByLostId(Integer id);
}
