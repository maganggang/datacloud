package com.fei2e.getlost.entity;

import javax.persistence.*;

@Table(name = "`get_file`")
public class GetFile {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column get_file.goods_file_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    private Integer goodsFileId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column get_file.get_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    private Integer getId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column get_file.goods_file_id
     *
     * @return the value of get_file.goods_file_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public Integer getGoodsFileId() {
        return goodsFileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column get_file.goods_file_id
     *
     * @param goodsFileId the value for get_file.goods_file_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public void setGoodsFileId(Integer goodsFileId) {
        this.goodsFileId = goodsFileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column get_file.get_id
     *
     * @return the value of get_file.get_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public Integer getGetId() {
        return getId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column get_file.get_id
     *
     * @param getId the value for get_file.get_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public void setGetId(Integer getId) {
        this.getId = getId;
    }
}