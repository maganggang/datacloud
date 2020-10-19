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
public interface MatchRecordService {
    int deleteByLostId(Integer lostId);

    int deleteByGetId(Integer id);
}
