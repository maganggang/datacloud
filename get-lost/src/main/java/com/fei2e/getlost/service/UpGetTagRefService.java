package com.fei2e.getlost.service;


import com.fei2e.getlost.entity.GoodsTag;

import java.util.List;

/**
 * @ClassName UpGetTagService
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/15 11:19
 * @Version 1.0
 **/
public interface UpGetTagRefService {
    void insertList(Integer goodsId, List<GoodsTag> tagList);

    int deleteByGetId(Integer id);

    List<GoodsTag> selectByGetId(Integer id);
}
