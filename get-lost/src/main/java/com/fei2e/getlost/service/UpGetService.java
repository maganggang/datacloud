package com.fei2e.getlost.service;

import com.fei2e.getlost.base.BaseService;
import com.fei2e.getlost.entity.BaseResult;
import com.fei2e.getlost.entity.Page;
import com.fei2e.getlost.entity.UpGet;

/**
 * @ClassName UpGetService
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/19 13:46
 * @Version 1.0
 **/
public interface UpGetService extends BaseService<UpGet> {
    BaseResult<Page<UpGet>> selectPage(Page<UpGet> page);

}
