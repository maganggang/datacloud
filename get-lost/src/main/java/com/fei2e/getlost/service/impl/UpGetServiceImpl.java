package com.fei2e.getlost.service.impl;

import com.fei2e.getlost.base.BaseMapper;
import com.fei2e.getlost.base.BaseServiceImpl;
import com.fei2e.getlost.entity.BaseResult;
import com.fei2e.getlost.entity.Page;
import com.fei2e.getlost.entity.UpGet;
import com.fei2e.getlost.mapper.UpGetMapper;
import com.fei2e.getlost.service.UpGetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UpGetServiceImpl
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/19 13:48
 * @Version 1.0
 **/
@Service
public class UpGetServiceImpl extends BaseServiceImpl<UpGet> implements UpGetService {
    @Autowired
    private UpGetMapper upGetMapper;

    @Override
    protected BaseMapper<UpGet> getMapper() {
        return upGetMapper;
    }

    @Override
    public BaseResult<Page<UpGet>> selectPage(Page<UpGet> page) {
        BaseResult<Page<UpGet>> result=new BaseResult<>();
        Page<UpGet> data=super.selectByPage(page);
        result.setData(data);
        return result;
    }

}
