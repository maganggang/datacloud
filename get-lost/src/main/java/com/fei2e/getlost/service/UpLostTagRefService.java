package com.fei2e.getlost.service;

import com.fei2e.getlost.entity.GoodsTag;

import java.util.List;

/**
 * @ClassName UpLostTagRefService
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/15 11:40
 * @Version 1.0
 **/
public interface UpLostTagRefService {
    public void insertList(Integer getId, List<GoodsTag> tagList);

    int deleteByLostId(Integer lostId);

    List<GoodsTag> selectByLost(Integer id);
}
