package com.fei2e.getlost.entity;

import javax.persistence.*;

@Table(name = "up_get_tag_ref")
public class UpGetTagRef {
    @Column(name = "get_id")
    private Integer getId;

    @Column(name = "goods_tag_id")
    private Integer goodsTagId;

    /**
     * @return get_id
     */
    public Integer getGetId() {
        return getId;
    }

    /**
     * @param getId
     */
    public void setGetId(Integer getId) {
        this.getId = getId;
    }

    /**
     * @return goods_tag_id
     */
    public Integer getGoodsTagId() {
        return goodsTagId;
    }

    /**
     * @param goodsTagId
     */
    public void setGoodsTagId(Integer goodsTagId) {
        this.goodsTagId = goodsTagId;
    }
}