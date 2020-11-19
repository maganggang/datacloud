package com.fei2e.getlost.controller;

import com.fei2e.getlost.entity.BaseResult;
import com.fei2e.getlost.entity.Goods;
import com.fei2e.getlost.entity.UpGet;
import com.fei2e.getlost.entity.UpLost;
import com.fei2e.getlost.service.GoodsService;
import com.fei2e.getlost.service.UpGetService;
import com.fei2e.getlost.service.UpLostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName GoodsController
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/14 15:54
 * @Version 1.0
 **/
@Api(description = "物品管理接口")
@RestController
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UpLostService upLostService;
    @Autowired
    private UpGetService upGetService;
    @ApiOperation(value = "修改", notes="修改物品")
    @PostMapping("update")
    public BaseResult<Boolean> update(@ApiParam(name = "goods", value = "物品", required = true)@RequestBody Goods goods){
        BaseResult<Boolean> result=new BaseResult<>();
      int i=goodsService.updateByPrimaryKeySelective(goods);
    if(i>0){
       result.setStatus(200);
       result.setData(true);
    }
    return result;
    }
    //修改物品
    @ApiOperation(value = "修改",notes = "通过丢失上报修改丢失物品")
    @PutMapping("lost/update/{lostId}")
    public BaseResult<Boolean> updateByLostId(@ApiParam(name = "lostId",required = true,value = "上报ID") @PathVariable Integer lostId,
                                              @ApiParam(name = "goods",required = true,value = "物品")@RequestBody Goods goods){
        UpLost upLost=upLostService.selectByPrimaryKey(lostId);
        int goodId=upLost.getGoodsId();
        goods.setGoodsId(goodId);
        return update(goods);
    }
    //修改物品
    @ApiOperation(value = "修改",notes = "通过拾取上报修改物品")
    @PutMapping("get/update/{getId}")
    public BaseResult<Boolean> updateByGetId(@ApiParam(name = "getId",required = true,value = "上报ID") @PathVariable Integer getId,
                                              @ApiParam(name = "goods",required = true,value = "物品")@RequestBody Goods goods){
        UpGet upGet=upGetService.selectByPrimaryKey(getId);
        int goodId=upGet.getGoodsId();
        goods.setGoodsId(goodId);
        return update(goods);
    }
}
