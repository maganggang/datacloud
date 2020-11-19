package com.fei2e.getlost.controller;

import com.fei2e.demo.entity.FileBase;
import com.fei2e.getlost.entity.*;
import com.fei2e.getlost.feign.FileFeignServer;
import com.fei2e.getlost.service.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName UpGetController
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/10/19 13:31
 * @Version 1.0
 **/
@Api(description = "上报拾取接口")
@RestController
@RequestMapping("upGet")
public class UpGetController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private UpGetService upGetService;
    @Autowired
    private UpGetTagRefService upGetTagRefService;
    @Autowired
    private GetFileService getFileService;
    @Autowired
    private FileFeignServer fileFeignServer;
    @Autowired
    private MatchRecordService matchRecordService;
    @Autowired
    private GoodsExtraService goodsExtraService;
    @ApiOperation(value = "新增", notes="上报拾到物品信息")
    @PostMapping("/add")
    public BaseResult<UpGet> add(@ApiParam(value = "拾取物品信息",name = "upGet")@RequestBody UpGet upGet){
        BaseResult<UpGet> baseResult=new BaseResult<>();
        if(request.getSession().getAttribute("userId")!=null){
            Integer userId=Integer.parseInt(request.getSession().getAttribute("userId").toString());
            Integer accountId=Integer.parseInt(request.getSession().getAttribute("accountId").toString());
            upGet.setUserId(userId);
            upGet.setCreateTime(new Date());
            upGet.setCreatorId(accountId);
            //插入物品
            if(upGet.getGoods()!=null){
                goodsService.insertSelective(upGet.getGoods());
                upGet.setGoodsId(upGet.getGoods().getGoodsId());
                //物品的颜色进行插入
                if(upGet.getColorCodes()!=null&&upGet.getColorCodes().size()>0){
                    goodsService.insertColors(upGet.getColorCodes(), upGet.getGoodsId());
                }
                //附加参数的插入
                if(upGet.getGoodsExtraList()!=null&&upGet.getGoodsExtraList().size()>0){
                    goodsExtraService.insertListByGoodsId(upGet.getGoodsExtraList(),upGet.getGoodsId());
                }
            }
            //插入位置
            if(upGet.getPosition()!=null){
                positionService.insertByCheck(upGet.getPosition());
                upGet.setPositionId(upGet.getPosition().getPositionId());
            }
            int i=upGetService.insert(upGet);
            //插入标签
            if(upGet.getGoodsTags()!=null&&upGet.getGoodsTags().size()>0){
                upGetTagRefService.insertList(upGet.getGetId(),upGet.getGoodsTags());
            }
            //插入附件
            if(upGet.getFileBases()!=null&&upGet.getFileBases().size()>0){
                getFileService.insertList(upGet.getGetId(),upGet.getFileBases());
                List<Integer> ids=new ArrayList<>();
                for (FileBase fileBase :upGet.getFileBases()) {
                    ids.add(fileBase.getId());
                }
                //附件ID绑定
                fileFeignServer.updateFileCount(ids);
            }

            if(i>0){
                baseResult.setStatus(200);
                baseResult.setData(upGet);
            }
        }else {
            baseResult.setStatus(500);
        }
        return baseResult;
    }
    @ApiOperation(value = "修改", notes="修改上报拾取物品信息")
    @PutMapping("/update/{id}")
    public BaseResult<UpGet> add(@PathVariable Integer id,@ApiParam(value = "拾取物品信息") @RequestBody UpGet upGet){
        BaseResult<UpGet> baseResult=new BaseResult<>();
        upGet.setGetId(id);
        Integer accountId=Integer.parseInt(request.getSession().getAttribute("accountId").toString());
        upGet.setOperatorId(accountId);
        upGet.setOperateTime(new Date());
        if(upGet.getGetTime()==null){
            upGet.setGetTime(new Date());
        }
        //修改物品
        if(upGet.getGoods()!=null&&upGet.getGoods().getGoodsId()!=null){
            goodsService.updateByPrimaryKey(upGet.getGoods());
        }
        //绑定位置
        if(upGet.getPosition()!=null){
            positionService.insertByCheck(upGet.getPosition());
            upGet.setPositionId(upGet.getPosition().getPositionId());
        }
        int i=upGetService.updateByPrimaryKeySelective(upGet);
        if(i>0){
            baseResult.setStatus(200);
            baseResult.setData(upGet);
        }

        return baseResult;
    }
    //查询我的丢失
    @ApiOperation(value = "查询", notes="查询我的拾取")
    @GetMapping("/select")
    public BaseResult<Page<UpGet>> selectPage(
            @ApiParam(name="page",value="分页",required=true) Page<UpGet> page){
        Integer accountId=Integer.parseInt(request.getSession().getAttribute("accountId").toString());
        UpGet upGet=page.getT();
        if(upGet==null){
            upGet=new UpGet();
            upGet.setCreatorId(accountId);
        }
        return  upGetService.selectPage(page);
    }
    //删除我的丢失
    @ApiOperation(value = "删除", notes="删除我的上传上报")
    @DeleteMapping("/delete/{id}")
    public BaseResult<Boolean> delete(@PathVariable Integer id){
        BaseResult<Boolean> result=new BaseResult<>();
        UpGet upGet=upGetService.selectByPrimaryKey(id);
        //删除附件关联
        List<Integer> fileIds=getFileService.selectFileByGetId(id);
        getFileService.deleteFileByGetId(id);
        fileFeignServer.removeFiles(fileIds);
        //删除tag关联
        upGetTagRefService.deleteByGetId(id);
        //删除物品关联
        goodsService.deleteByGetId(id);
        //删除匹配记录
        matchRecordService.deleteByGetId(id);
        int i=upGetService.deleteByPrimaryKey(id);
        if(i>0){
            result.setData(true);
        }
        return result;
    }
    @ApiOperation(value = "查询", notes="查看我的丢失详情")
    @GetMapping("view/{id}")
    public BaseResult<UpGet> view(@PathVariable Integer id){
        BaseResult<UpGet> result=new BaseResult<>();
        UpGet upGet=upGetService.selectByPrimaryKey(id);
        //获取附件信息
        List<FileBase> getFileBase=getFileService.selectFileBaseByGetId(id);
        upGet.setFileBases(getFileBase);
        Goods goods=goodsService.selectByPrimaryKey(upGet.getGoodsId());
        upGet.setGoods(goods);
        Position position=positionService.selectByPrimaryKey(upGet.getPositionId());
        upGet.setPosition(position);
        List<GoodsExtra> goodsExtras=goodsService.selectGoodsExtra(upGet.getGoodsId());
        List<Dictionary> colors=goodsService.selectGoodsColor(upGet.getGoodsId());
        upGet.setGoodsExtraList(goodsExtras);
        upGet.setColors(colors);
        List<GoodsTag> tagRefs=upGetTagRefService.selectByGetId(id);
        upGet.setGoodsTags(tagRefs);
        result.setData(upGet);
        return result;
    }
}
