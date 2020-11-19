package com.fei2e.getlost.mapper;

import com.fei2e.getlost.entity.GoodsColorRef;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface GoodsColorRefMapper extends Mapper<GoodsColorRef> {
    List<String> selectCode(Integer goodsId);
}