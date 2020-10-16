package com.fei2e.getlost.service;

import com.fei2e.getlost.base.BaseService;
import com.fei2e.getlost.entity.Goods;
import com.fei2e.getlost.service.impl.UpLostTagRefServiceImpl;

/**
 * @ClassName GoodsService
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/27 12:19
 * @Version 1.0
 **/
public interface GoodsService extends BaseService<Goods> {

    int deleteByLostId(Integer lostId);

    /**
     * @ClassName UpLostTagRefService
     * @DescripTion TODO
     * @Author dell
     * @Date 2020/10/15 11:41
     * @Version 1.0
     **/
    class UpLostTagRefService  extends UpLostTagRefServiceImpl {
    }
}
