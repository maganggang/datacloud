package com.fei2e.getlost.service.impl;

import com.fei2e.getlost.entity.MatchRecord;
import com.fei2e.getlost.mapper.MatchRecordMapper;
import com.fei2e.getlost.service.MatchRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName MatchRecordServiceImpl
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/16 16:37
 * @Version 1.0
 **/
@Service
public class MatchRecordServiceImpl implements MatchRecordService {
    @Autowired
    MatchRecordMapper matchRecordMapper;
    @Override
    public int deleteByLostId(Integer lostId) {
        MatchRecord matchRecord=new MatchRecord();
        matchRecord.setLostId(lostId);
        return matchRecordMapper.delete(matchRecord);
    }
}
