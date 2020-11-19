package com.fei2e.getlost.service.impl;

import com.fei2e.getlost.base.BaseMapper;
import com.fei2e.getlost.base.BaseServiceImpl;
import com.fei2e.getlost.entity.Position;
import com.fei2e.getlost.mapper.PositionMapper;
import com.fei2e.getlost.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName PositionServiceImpl
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/15 15:36
 * @Version 1.0
 **/
@Service
public class PositionServiceImpl extends BaseServiceImpl<Position> implements PositionService {
    @Autowired
    private PositionMapper positionMapper;
    @Override
    protected BaseMapper<Position> getMapper() {
        return positionMapper;
    }

    @Override
    public int insertByCheck(Position position) {
        Position position1=new Position();
        position1.setLng(position.getLng());
        position1.setLat(position.getLat());
        position1=positionMapper.selectOne(position);
        if (position1!=null&&position1.getPositionId()!=null){
            position=position1;
        }else{
            return positionMapper.insert(position);
        }
        return 1;
    }
}
