package com.fei2e.getlost.service;

import com.fei2e.getlost.base.BaseService;
import com.fei2e.getlost.entity.Dictionary;
import com.fei2e.getlost.entity.Goods;
import com.fei2e.getlost.entity.GoodsExtra;

import java.util.List;

/**
 * @ClassName GoodsService
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/27 12:19
 * @Version 1.0
 **/
public interface GoodsService extends BaseService<Goods> {

    int deleteByLostId(Integer lostId);

    int insertColors(List<String> colorCodes, Integer goodsId);

    int deleteByGetId(Integer id);

    List<GoodsExtra> selectGoodsExtra(Integer goodsId);

    List<Dictionary> selectGoodsColor(Integer goodsId);
}
