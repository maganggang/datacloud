package com.fei2e.getlost.controller;

import com.fei2e.getlost.entity.BaseResult;
import com.fei2e.getlost.entity.UpLost;
import com.fei2e.getlost.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UpLostController
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/15 9:18
 * @Version 1.0
 **/
@Api(description = "上报丢失接口")
@RestController
@RequestMapping("upLost")
public class UpLostController {
    @Autowired
    private UpLostService upLostService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UpLostTagRefService upLostTagRefService;
    @Autowired
    private LostFileService lostFileService;
    @Autowired
    private PositionService positionService;
    @ApiOperation(value = "新增", notes="上报丢失物品信息")
    @ApiImplicitParam(name = "upLost", value = "丢失信息", paramType = "body", required = true, dataType = "UpLost")
    @PutMapping("/add")
    public BaseResult<UpLost> add(@RequestBody UpLost upLost){
        BaseResult<UpLost> baseResult=new BaseResult<>();
        if(request.getSession().getAttribute("userId")!=null){
            //插入物品
            if(upLost.getGoods()!=null){
                goodsService.insertSelective(upLost.getGoods());
                upLost.setGoodsId(upLost.getGoods().getGoodsId());
            }
            //插入位置
            if(upLost.getPosition()!=null){
                positionService.insertSelective(upLost.getPosition());
                upLost.setPositionId(upLost.getPosition().getPositionId());
            }
            int i=upLostService.insert(upLost);
            //插入标签
            if(upLost.getTagList()!=null&&upLost.getTagList().size()>0){
                upLostTagRefService.insertList(upLost.getLostId(),upLost.getTagList());
            }
            //插入附件
            if(upLost.getLostFiles()!=null&&upLost.getLostFiles().size()>0){
                lostFileService.insertList(upLost.getLostId(),upLost.getLostFiles());
            }

            if(i>0){
                baseResult.setStatus(200);
                baseResult.setData(upLost);
            }
        }else {
            baseResult.setStatus(500);
        }

        return baseResult;
    }
}
