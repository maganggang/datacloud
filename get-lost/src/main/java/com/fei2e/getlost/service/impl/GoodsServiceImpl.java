package com.fei2e.getlost.service.impl;

import com.fei2e.getlost.base.BaseMapper;
import com.fei2e.getlost.base.BaseServiceImpl;
import com.fei2e.getlost.entity.*;
import com.fei2e.getlost.mapper.*;
import com.fei2e.getlost.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GoodsServiceImpl
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/15 10:46
 * @Version 1.0
 **/
@Service
public class GoodsServiceImpl  extends BaseServiceImpl<Goods>  implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private UpLostMapper upLostMapper;
    @Autowired
    private GoodsColorRefMapper goodsColorRefMapper;
    @Autowired
    private UpGetMapper upGetMapper;
    @Autowired
    private GoodsExtraMapper goodsExtraMapper;
    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Override
    protected BaseMapper<Goods> getMapper() {
        return goodsMapper;
    }

    @Override
    public int deleteByLostId(Integer lostId) {
        UpLost upLost=upLostMapper.selectByPrimaryKey(lostId);
        return goodsMapper.deleteByPrimaryKey(upLost.getGoodsId());
    }

    @Override
    public int insertColors(List<String> colorCodes, Integer goodsId) {
       for(String code:colorCodes){
           GoodsColorRef goodsColorRef=new GoodsColorRef();
           goodsColorRef.setGoodsId(goodsId);
           goodsColorRef.setColorCode(code);
           goodsColorRefMapper.insert(goodsColorRef);
       }
        return 1;
    }

    @Override
    public int deleteByGetId(Integer id) {
        UpGet upGet=upGetMapper.selectByPrimaryKey(id);
        GoodsColorRef goodsColorRef=new GoodsColorRef();
        goodsColorRef.setGoodsId(upGet.getGoodsId());
        GoodsExtra goodsExtra=new GoodsExtra();
        goodsExtra.setGoodsId(upGet.getGoodsId());
        //删除颜色
        goodsColorRefMapper.delete( goodsColorRef);
        //删除扩展
        goodsExtraMapper.delete(goodsExtra);
        return getMapper().deleteByPrimaryKey(id);
    }

    @Override
    public List<GoodsExtra> selectGoodsExtra(Integer goodsId) {
        GoodsExtra goodsExtra=new GoodsExtra();
        goodsExtra.setGoodsId(goodsId);
        return goodsExtraMapper.select(goodsExtra);
    }

    @Override
    public List<Dictionary> selectGoodsColor(Integer goodsId) {
        List<String> colorList=goodsColorRefMapper.selectCode(goodsId);
        List<Dictionary> colors= dictionaryMapper.selectByCodes(colorList);
        return colors;
    }

}
