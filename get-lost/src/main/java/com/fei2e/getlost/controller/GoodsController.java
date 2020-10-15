package com.fei2e.getlost.controller;

import com.fei2e.getlost.entity.BaseResult;
import com.fei2e.getlost.entity.Goods;
import com.fei2e.getlost.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    @ApiOperation(value = "修改", notes="修改物品")
    @ApiImplicitParam(name = "goods", value = "物品", paramType = "body", required = true, dataType = "Goods")
    @PostMapping("update")
    public BaseResult<Boolean> update(@RequestBody Goods goods){
        BaseResult<Boolean> result=new BaseResult<>();
      int i=goodsService.updateByPrimaryKeySelective(goods);
    if(i>0){
       result.setStatus(200);
       result.setData(true);
    }
    return result;
    }
    @GetMapping("test")
    public String test(){
        HttpSession session=request.getSession();
        System.out.println(session.getAttribute("accountId"));
       return "hello";
    }
}
