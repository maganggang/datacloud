package com.fei2e.getlost.entity;

import com.fei2e.demo.entity.FileBase;

import javax.persistence.*;

@Table(name = "`lost_file`")
public class LostFile {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lost_file.goods_file_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    private Integer goodsFileId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lost_file.lost_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    private Integer lostId;
    @Transient
    private FileBase fileBase;

    public FileBase getFileBase() {
        return fileBase;
    }

    public void setFileBase(FileBase fileBase) {
        this.fileBase = fileBase;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lost_file.goods_file_id
     *
     * @return the value of lost_file.goods_file_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public Integer getGoodsFileId() {
        return goodsFileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lost_file.goods_file_id
     *
     * @param goodsFileId the value for lost_file.goods_file_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public void setGoodsFileId(Integer goodsFileId) {
        this.goodsFileId = goodsFileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lost_file.lost_id
     *
     * @return the value of lost_file.lost_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public Integer getLostId() {
        return lostId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lost_file.lost_id
     *
     * @param lostId the value for lost_file.lost_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public void setLostId(Integer lostId) {
        this.lostId = lostId;
    }
}