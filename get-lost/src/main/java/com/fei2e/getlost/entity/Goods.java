package com.fei2e.getlost.entity;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "`goods`")
public class Goods {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.goods_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer goodsId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.name
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.weight
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    private BigDecimal weight;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.shape
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    private String shape;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column goods.color
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    private String color;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.goods_id
     *
     * @return the value of goods.goods_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.goods_id
     *
     * @param goodsId the value for goods.goods_id
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.name
     *
     * @return the value of goods.name
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.name
     *
     * @param name the value for goods.name
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.weight
     *
     * @return the value of goods.weight
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.weight
     *
     * @param weight the value for goods.weight
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.shape
     *
     * @return the value of goods.shape
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public String getShape() {
        return shape;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.shape
     *
     * @param shape the value for goods.shape
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public void setShape(String shape) {
        this.shape = shape;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column goods.color
     *
     * @return the value of goods.color
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public String getColor() {
        return color;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column goods.color
     *
     * @param color the value for goods.color
     *
     * @mbggenerated Mon Sep 28 17:24:23 CST 2020
     */
    public void setColor(String color) {
        this.color = color;
    }
}