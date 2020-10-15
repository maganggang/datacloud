package com.fei2e.getlost.service.impl;

import com.fei2e.getlost.entity.GoodsTag;
import com.fei2e.getlost.entity.UpGetTagRef;
import com.fei2e.getlost.mapper.UpGetTagRefMapper;
import com.fei2e.getlost.service.UpGetTagRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UpGetTagServiceImpl
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/15 11:20
 * @Version 1.0
 **/
@Service
public class UpGetTagServiceImpl implements UpGetTagRefService {
    @Autowired
    private UpGetTagRefMapper upGetTagRefMapper;

    @Transactional
    @Override
    public void insertList(Integer getId, List<GoodsTag> tagList) {
        for (GoodsTag goodsTag:tagList){
            UpGetTagRef upGetTagRef=new UpGetTagRef();
            upGetTagRef.setGoodsTagId(upGetTagRef.getGoodsTagId());
            upGetTagRef.setGetId(getId);
            upGetTagRefMapper.insertSelective(upGetTagRef);
        }
    }
}
