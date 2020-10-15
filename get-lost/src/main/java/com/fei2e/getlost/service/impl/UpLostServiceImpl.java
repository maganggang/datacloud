package com.fei2e.getlost.service.impl;

import com.fei2e.getlost.base.BaseMapper;
import com.fei2e.getlost.base.BaseServiceImpl;
import com.fei2e.getlost.entity.Goods;
import com.fei2e.getlost.entity.UpLost;
import com.fei2e.getlost.mapper.GoodsMapper;
import com.fei2e.getlost.mapper.UpLostMapper;
import com.fei2e.getlost.service.GoodsService;
import com.fei2e.getlost.service.UpLostService;
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
public class UpLostServiceImpl extends BaseServiceImpl<UpLost>  implements UpLostService {
    @Autowired
    private UpLostMapper upLostMapper;
    @Override
    protected BaseMapper<UpLost> getMapper() {
        return upLostMapper;
    }
}
