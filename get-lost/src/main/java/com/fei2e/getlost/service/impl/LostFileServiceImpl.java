package com.fei2e.getlost.service.impl;

import com.fei2e.demo.entity.FileBase;
import com.fei2e.getlost.entity.LostFile;
import com.fei2e.getlost.feign.FileFeignServer;
import com.fei2e.getlost.mapper.LostFileMapper;
import com.fei2e.getlost.service.LostFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LostFileServiceImpl
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/15 11:55
 * @Version 1.0
 **/
@Service
public class LostFileServiceImpl implements LostFileService {
    @Autowired
    private LostFileMapper lostFileMapper;
    @Autowired
    private FileFeignServer fileFeignServer;
    @Override
    public List<LostFile> selectByLostId(Integer lostId) {
        LostFile lostFile=new LostFile();
        lostFile.setLostId(lostId);
        List<LostFile> lostFiles=lostFileMapper.select(lostFile);
       List<Integer> list=new ArrayList<>();
        for (LostFile file :lostFiles ) {
            list.add(file.getGoodsFileId());
        }
        List<FileBase> fileBases=fileFeignServer.selectByIds(list);
        for ( FileBase file:fileBases ) {
            for (LostFile lost: lostFiles ) {
                if(file.getId().equals(lost.getGoodsFileId())){
                    lost.setFileBase(file);
                }
            }
        }
        return lostFiles;
    }

    @Transactional
    @Override
    public void insertList(Integer lostId, List<LostFile> lostFiles) {
        for (LostFile lostFile:lostFiles){
            lostFile.setLostId(lostId);
            lostFileMapper.insertSelective(lostFile);
        }
    }

    @Override
    public int deleteFileByLostId(Integer id) {
      LostFile lostFile=new LostFile();
      lostFile.setLostId(id);
        return lostFileMapper.delete(lostFile);
    }

    @Override
    public List<Integer> selectFileByLostId(Integer lostId) {
        return lostFileMapper.selectFileByLostId(lostId);
    }
}
