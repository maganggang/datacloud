package com.fei2e.getlost.service;

import com.fei2e.getlost.base.BaseService;
import com.fei2e.getlost.entity.BaseResult;
import com.fei2e.getlost.entity.Goods;
import com.fei2e.getlost.entity.Page;
import com.fei2e.getlost.entity.UpLost;

/**
 * @ClassName UpLostService
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/15 9:19
 * @Version 1.0
 **/
public interface UpLostService extends BaseService<UpLost> {
    BaseResult<Page<UpLost>> selectPage(Page<UpLost> page);
}
