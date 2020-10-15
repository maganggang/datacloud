package com.fei2e.getlost.entity;

import javax.persistence.*;

@Table(name = "up_lost_tag_ref")
public class UpLostTagRef {
    @Column(name = "lost_id")
    private Integer lostId;

    @Column(name = "goods_tag_id")
    private Integer goodsTagId;

    /**
     * @return lost_id
     */
    public Integer getLostId() {
        return lostId;
    }

    /**
     * @param lostId
     */
    public void setLostId(Integer lostId) {
        this.lostId = lostId;
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