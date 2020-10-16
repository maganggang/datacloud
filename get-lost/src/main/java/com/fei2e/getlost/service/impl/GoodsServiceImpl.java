package com.fei2e.getlost.service.impl;

import com.fei2e.getlost.base.BaseMapper;
import com.fei2e.getlost.base.BaseServiceImpl;
import com.fei2e.getlost.entity.Goods;
import com.fei2e.getlost.entity.UpLost;
import com.fei2e.getlost.mapper.AccountMapper;
import com.fei2e.getlost.mapper.GoodsMapper;
import com.fei2e.getlost.mapper.UpLostMapper;
import com.fei2e.getlost.service.GoodsService;
import org.checkerframework.checker.units.qual.A;
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
    @Autowired
    private UpLostMapper upLostMapper;
    @Override
    protected BaseMapper<Goods> getMapper() {
        return goodsMapper;
    }

    @Override
    public int deleteByLostId(Integer lostId) {
        UpLost upLost=upLostMapper.selectByPrimaryKey(lostId);
        return goodsMapper.deleteByPrimaryKey(upLost.getGoodsId());
    }
}
