package com.fei2e.getlost.service;

import com.fei2e.getlost.base.BaseService;
import com.fei2e.getlost.entity.GoodsExtra;
import com.fei2e.getlost.entity.Position;

import java.util.List;

/**
 * @ClassName GoodsExtraService
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/11/4 10:26
 * @Version 1.0
 **/
public interface GoodsExtraService extends BaseService<GoodsExtra> {
    int insertListByGoodsId(List<GoodsExtra> goodsExtraList, Integer goodsId);
}
