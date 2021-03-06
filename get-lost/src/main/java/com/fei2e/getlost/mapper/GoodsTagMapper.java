package com.fei2e.getlost.mapper;

import com.fei2e.getlost.entity.GoodsTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
@Repository
public interface GoodsTagMapper extends Mapper<GoodsTag> {
    List<GoodsTag> selectByIds(@Param("list") List<Integer> list);
}