package com.fei2e.getlost.service.impl;

import com.fei2e.demo.entity.FileBase;
import com.fei2e.getlost.entity.GetFile;
import com.fei2e.getlost.feign.FileFeignServer;
import com.fei2e.getlost.mapper.GetFileMapper;
import com.fei2e.getlost.service.GetFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GetFileServiceImpl
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/19 14:05
 * @Version 1.0
 **/
@Service
public class GetFileServiceImpl implements GetFileService {
    @Autowired
    private GetFileMapper getFileMapper;
    @Autowired
    private FileFeignServer fileFeignServer;
    @Override
    public void insertList(Integer getId, List<FileBase> fileBases) {
        for (FileBase file :fileBases ) {
            GetFile getFile=new GetFile();
            getFile.setGetId(getId);
            getFile.setGoodsFileId(file.getId());
            getFileMapper.insert(getFile);
        }
    }

    @Override
    public List<Integer> selectFileByGetId(Integer getId) {
        GetFile getFile=new GetFile();
        getFile.setGetId(getId);
        List<GetFile> getFiles=getFileMapper.select(getFile);
        List<Integer> list=new ArrayList<>();
        for (GetFile file :getFiles ) {
            list.add(file.getGoodsFileId());
        }
        return list;
    }

    @Override
    public int deleteFileByGetId(Integer id) {
        GetFile getFile=new GetFile();
        getFile.setGetId(id);
        return getFileMapper.delete(getFile);
    }

    @Override
    public List<FileBase> selectFileBaseByGetId(Integer id) {
        GetFile getFile=new GetFile();
        getFile.setGetId(id);
        List<GetFile> getFiles=getFileMapper.select(getFile);
        List<Integer> list=new ArrayList<>();
        for (GetFile file :getFiles ) {
            list.add(file.getGoodsFileId());
        }
        List<FileBase> fileBases=fileFeignServer.selectByIds(list);
        return fileBases;
    }

}
