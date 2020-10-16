package com.fei2e.getlost.mapper;

import com.fei2e.getlost.entity.LostFile;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
@Repository
public interface LostFileMapper extends Mapper<LostFile> {
    List<Integer> selectFileByLostId(Integer lostId);
}