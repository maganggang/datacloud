package com.fei2e.getlost.entity;

import com.fei2e.demo.entity.FileBase;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "`up_get`")
public class UpGet {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column up_get.get_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer getId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column up_get.user_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column up_get.position_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    private Integer positionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column up_get.goods_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    private Integer goodsId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column up_get.title
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column up_get.content
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column up_get.get_time
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    private Date getTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column up_get.operator_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    private Integer operatorId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column up_get.operate_time
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    private Date operateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column up_get.create_time
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column up_get.creator_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    private Integer creatorId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column up_get.telephone
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    private String telephone;
    private String address;
    @Transient
    private Goods goods;
    @Transient
    private Position position;
    @Transient
    private List<GoodsTag> goodsTags;
    @Transient
    private List<FileBase> fileBases;
    @Transient
    private List<Dictionary> colors;
    @Transient
    private List<String> colorCodes;
    @Transient
    private List<Integer> tagIds;
    @Transient
    private List<Integer> fileIds;
    @Transient
    private List<GoodsExtra> goodsExtraList;

    public List<GoodsExtra> getGoodsExtraList() {
        return goodsExtraList;
    }

    public void setGoodsExtraList(List<GoodsExtra> goodsExtraList) {
        this.goodsExtraList = goodsExtraList;
    }

    public List<Dictionary> getColors() {
        return colors;
    }

    public void setColors(List<Dictionary> colors) {
        this.colors = colors;
    }

    public List<String> getColorCodes() {
        return colorCodes;
    }

    public void setColorCodes(List<String> colorCodes) {
        this.colorCodes = colorCodes;
    }

    public List<Integer> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Integer> tagIds) {
        this.tagIds = tagIds;
    }

    public List<Integer> getFileIds() {
        return fileIds;
    }

    public void setFileIds(List<Integer> fileIds) {
        this.fileIds = fileIds;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<GoodsTag> getGoodsTags() {
        return goodsTags;
    }

    public void setGoodsTags(List<GoodsTag> goodsTags) {
        this.goodsTags = goodsTags;
    }

    public List<FileBase> getFileBases() {
        return fileBases;
    }

    public void setFileBases(List<FileBase> fileBases) {
        this.fileBases = fileBases;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column up_get.get_id
     *
     * @return the value of up_get.get_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public Integer getGetId() {
        return getId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column up_get.get_id
     *
     * @param getId the value for up_get.get_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public void setGetId(Integer getId) {
        this.getId = getId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column up_get.user_id
     *
     * @return the value of up_get.user_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column up_get.user_id
     *
     * @param userId the value for up_get.user_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column up_get.position_id
     *
     * @return the value of up_get.position_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public Integer getPositionId() {
        return positionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column up_get.position_id
     *
     * @param positionId the value for up_get.position_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column up_get.goods_id
     *
     * @return the value of up_get.goods_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column up_get.goods_id
     *
     * @param goodsId the value for up_get.goods_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column up_get.title
     *
     * @return the value of up_get.title
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column up_get.title
     *
     * @param title the value for up_get.title
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column up_get.content
     *
     * @return the value of up_get.content
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column up_get.content
     *
     * @param content the value for up_get.content
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column up_get.get_time
     *
     * @return the value of up_get.get_time
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public Date getGetTime() {
        return getTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column up_get.get_time
     *
     * @param getTime the value for up_get.get_time
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column up_get.operator_id
     *
     * @return the value of up_get.operator_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public Integer getOperatorId() {
        return operatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column up_get.operator_id
     *
     * @param operatorId the value for up_get.operator_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column up_get.operate_time
     *
     * @return the value of up_get.operate_time
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public Date getOperateTime() {
        return operateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column up_get.operate_time
     *
     * @param operateTime the value for up_get.operate_time
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column up_get.create_time
     *
     * @return the value of up_get.create_time
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column up_get.create_time
     *
     * @param createTime the value for up_get.create_time
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column up_get.creator_id
     *
     * @return the value of up_get.creator_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column up_get.creator_id
     *
     * @param creatorId the value for up_get.creator_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column up_get.telephone
     *
     * @return the value of up_get.telephone
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column up_get.telephone
     *
     * @param telephone the value for up_get.telephone
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}