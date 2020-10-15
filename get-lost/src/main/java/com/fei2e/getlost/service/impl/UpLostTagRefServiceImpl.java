package com.fei2e.getlost.service.impl;

import com.fei2e.getlost.entity.GoodsTag;
import com.fei2e.getlost.entity.UpGetTagRef;
import com.fei2e.getlost.entity.UpLostTagRef;
import com.fei2e.getlost.mapper.UpLostTagRefMapper;
import com.fei2e.getlost.service.UpLostTagRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UpLostTagRefServiceImpl
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/15 11:43
 * @Version 1.0
 **/
@Service
public class UpLostTagRefServiceImpl implements UpLostTagRefService {
    @Autowired
    private UpLostTagRefMapper upLostTagRefMapper;
    @Override
    public void insertList(Integer getId, List<GoodsTag> tagList) {
        for (GoodsTag goodsTag:tagList){
            UpLostTagRef upGetTagRef=new UpLostTagRef();
            upGetTagRef.setGoodsTagId(goodsTag.getTagId());
            upGetTagRef.setLostId(getId);
            upLostTagRefMapper.insertSelective(upGetTagRef);
        }
    }
}
