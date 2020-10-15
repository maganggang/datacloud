package com.fei2e.getlost.service.impl;

import com.fei2e.getlost.entity.LostFile;
import com.fei2e.getlost.mapper.LostFileMapper;
import com.fei2e.getlost.service.LostFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName LostFileServiceImpl
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/15 11:55
 * @Version 1.0
 **/
@Service
public class LostFileServiceImpl implements LostFileService {
    @Autowired
    private LostFileMapper lostFileMapper;
    @Transactional
    @Override
    public void insertList(Integer lostId, List<LostFile> lostFiles) {
        for (LostFile lostFile:lostFiles){
            lostFile.setLostId(lostId);
            lostFileMapper.insertSelective(lostFile);
        }
    }
}
