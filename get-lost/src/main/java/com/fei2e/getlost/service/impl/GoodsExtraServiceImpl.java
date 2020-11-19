package com.fei2e.getlost.service.impl;

import com.fei2e.getlost.base.BaseMapper;
import com.fei2e.getlost.base.BaseServiceImpl;
import com.fei2e.getlost.entity.Goods;
import com.fei2e.getlost.entity.GoodsExtra;
import com.fei2e.getlost.mapper.GoodsExtraMapper;
import com.fei2e.getlost.service.GoodsExtraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName GoodsExtraServiceImpl
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/4 10:27
 * @Version 1.0
 **/
@Service
public class GoodsExtraServiceImpl extends BaseServiceImpl<GoodsExtra> implements GoodsExtraService {
    @Autowired
    private GoodsExtraMapper goodsExtraMapper;
    @Override
    protected BaseMapper<GoodsExtra> getMapper() {
        return goodsExtraMapper;
    }

    @Override
    public int insertListByGoodsId(List<GoodsExtra> goodsExtraList, Integer goodsId) {
        for(GoodsExtra goodsExtra:goodsExtraList){
            goodsExtra.setGoodsId(goodsId);
        }
       int i= goodsExtraMapper.insertList(goodsExtraList);
       return i;
    }
}
