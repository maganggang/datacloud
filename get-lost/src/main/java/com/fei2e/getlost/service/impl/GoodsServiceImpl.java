package com.fei2e.getlost.service.impl;

import com.fei2e.getlost.base.BaseMapper;
import com.fei2e.getlost.base.BaseServiceImpl;
import com.fei2e.getlost.entity.Goods;
import com.fei2e.getlost.mapper.AccountMapper;
import com.fei2e.getlost.mapper.GoodsMapper;
import com.fei2e.getlost.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName GoodsServiceImpl
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/15 10:46
 * @Version 1.0
 **/
@Service
public class GoodsServiceImpl  extends BaseServiceImpl<Goods>  implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    protected BaseMapper<Goods> getMapper() {
        return goodsMapper;
    }
}
