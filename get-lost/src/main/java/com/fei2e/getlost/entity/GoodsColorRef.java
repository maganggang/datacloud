package com.fei2e.getlost.entity;

import javax.persistence.*;

@Table(name = "goods_color_ref")
public class GoodsColorRef {
    @Column(name = "color_code")
    private String colorCode;

    @Column(name = "goods_id")
    private Integer goodsId;

    /**
     * @return color_code
     */
    public String getColorCode() {
        return colorCode;
    }

    /**
     * @param colorCode
     */
    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    /**
     * @return goods_id
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * @param goodsId
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
}