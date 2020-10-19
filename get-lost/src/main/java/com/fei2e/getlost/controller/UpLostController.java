package com.fei2e.getlost.controller;

import com.fei2e.getlost.entity.*;
import com.fei2e.getlost.feign.FileFeignServer;
import com.fei2e.getlost.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private FileFeignServer fileFeignServer;
    @Autowired
    private MatchRecordService matchRecordService;
    @ApiOperation(value = "新增", notes="上报丢失物品信息")
    @ApiImplicitParam(name = "upLost", value = "丢失信息", paramType = "body", required = true, dataType = "UpLost")
    @PostMapping("/add")
    public BaseResult<UpLost> add(@RequestBody UpLost upLost){
        BaseResult<UpLost> baseResult=new BaseResult<>();
        if(request.getSession().getAttribute("userId")!=null){
            Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
            Integer accountId=Integer.parseInt(request.getSession().getAttribute("accountId").toString());
            upLost.setUserId(userId);
            upLost.setCreateTime(new Date());
            upLost.setCreatorId(accountId);
            //插入物品
            if(upLost.getGoods()!=null){
                goodsService.insertSelective(upLost.getGoods());
                upLost.setGoodsId(upLost.getGoods().getGoodsId());
            }
            //插入位置
            if(upLost.getPosition()!=null){
                positionService.insertByCheck(upLost.getPosition());
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
                List<Integer> ids=new ArrayList<>();
                for (LostFile lostFile :upLost.getLostFiles()) {
                    ids.add(lostFile.getGoodsFileId());
                }
                //附件ID绑定
                fileFeignServer.updateFileCount(ids);
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
    @ApiOperation(value = "修改", notes="修改上报丢失物品信息")
    @ApiImplicitParam(name = "upLost", value = "丢失信息", paramType = "body", required = true, dataType = "UpLost")
    @PutMapping("/update/{id}")
    public BaseResult<UpLost> add(@PathVariable Integer id,@RequestBody UpLost upLost){
        BaseResult<UpLost> baseResult=new BaseResult<>();
            upLost.setLostId(id);
            Integer accountId=Integer.parseInt(request.getSession().getAttribute("accountId").toString());
            upLost.setOperatorId(accountId);
            upLost.setOperateTime(new Date());
            //修改物品
            if(upLost.getGoods()!=null&&upLost.getGoods().getGoodsId()!=null){
                goodsService.updateByPrimaryKey(upLost.getGoods());
            }
            //绑定位置
            if(upLost.getPosition()!=null){
                positionService.insertByCheck(upLost.getPosition());
                upLost.setPositionId(upLost.getPosition().getPositionId());
            }
            int i=upLostService.updateByPrimaryKeySelective(upLost);
            if(i>0){
                baseResult.setStatus(200);
                baseResult.setData(upLost);
            }

        return baseResult;
    }
    //查询我的丢失
    @ApiOperation(value = "查询", notes="查询我的丢失")
    @GetMapping("/select")
    public BaseResult<Page<UpLost>> selectPage(
            @ApiParam(name="page",value="分页",required=true) Page<UpLost> page){
        Integer accountId=Integer.parseInt(request.getSession().getAttribute("accountId").toString());
        UpLost upLost=page.getT();
        if(upLost==null){
            upLost=new UpLost();
            upLost.setCreatorId(accountId);
        }
        return  upLostService.selectPage(page);
    }
    //删除我的丢失
    @ApiOperation(value = "删除", notes="删除我的丢失上报")
    @DeleteMapping("/delete/{id}")
    public BaseResult<Boolean> delete(@PathVariable Integer id){
        BaseResult<Boolean> result=new BaseResult<>();
        //删除附件关联
        List<Integer> fileIds=lostFileService.selectFileByLostId(id);
        lostFileService.deleteFileByLostId(id);
        fileFeignServer.removeFiles(fileIds);
        //删除tag关联
        upLostTagRefService.deleteByLostId(id);
        //删除物品关联
        goodsService.deleteByLostId(id);
        //删除匹配记录
        matchRecordService.deleteByLostId(id);
        int i=upLostService.deleteByPrimaryKey(id);
        if(i>0){
            result.setData(true);
        }
        return result;
    }
    @ApiOperation(value = "查询", notes="查看我的丢失详情")
    @GetMapping("view/{id}")
    public BaseResult<UpLost> view(@PathVariable Integer id){
        BaseResult<UpLost> result=new BaseResult<>();
        UpLost upLost=upLostService.selectByPrimaryKey(id);
        List<LostFile> lostFiles=lostFileService.selectByLostId(id);
        upLost.setLostFiles(lostFiles);
        Goods goods=goodsService.selectByPrimaryKey(upLost.getGoodsId());
        upLost.setGoods(goods);
        Position position=positionService.selectByPrimaryKey(upLost.getPositionId());
        upLost.setPosition(position);
        List<GoodsTag> tagRefs=upLostTagRefService.selectByLost(id);
        upLost.setTagList(tagRefs);
        result.setData(upLost);
        return result;
    }

    //修改物品 ，修改tag ,修改附件
}
