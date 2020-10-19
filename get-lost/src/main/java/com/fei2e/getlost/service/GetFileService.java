package com.fei2e.getlost.service;

import com.fei2e.demo.entity.FileBase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GetFileService {
    void insertList(Integer getId, List<FileBase> fileBases);

    List<Integer> selectFileByGetId(Integer id);

    int deleteFileByGetId(Integer id);

    List<FileBase> selectFileBaseByGetId(Integer id);
}
