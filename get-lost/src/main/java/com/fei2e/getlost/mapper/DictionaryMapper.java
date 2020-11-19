package com.fei2e.getlost.mapper;

import com.fei2e.getlost.entity.Dictionary;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface DictionaryMapper extends Mapper<Dictionary> {
    List<Dictionary> selectByCodes(List<String> colorList);
}