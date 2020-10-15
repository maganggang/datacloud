package com.fei2e.getlost.entity;

import javax.persistence.*;

@Table(name = "match_record")
public class MatchRecord {
    @Id
    @Column(name = "match_id")
    private Integer matchId;

    @Column(name = "lost_id")
    private Integer lostId;

    @Column(name = "get_id")
    private Integer getId;

    /**
     * @return match_id
     */
    public Integer getMatchId() {
        return matchId;
    }

    /**
     * @param matchId
     */
    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

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
}