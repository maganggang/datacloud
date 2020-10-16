package com.fei2e.getlost.feign;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName FileError
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/16 9:24
 * @Version 1.0
 **/
@Service
public class FileError implements FileFeignServer{
    @Override
    public String hello() {
        return null;
    }

    @Override
    public int updateFileCount(List<Integer> ids) {
        return 0;
    }

    @Override
    public boolean removeFiles(List<Integer> fileIds) {
        return false;
    }
}
