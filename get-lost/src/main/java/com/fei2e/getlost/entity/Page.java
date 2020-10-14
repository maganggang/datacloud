package com.fei2e.getlost.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Page
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/27 11:10
 * @Version 1.0
 **/
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    //当前页码
    private int pageSize = 1;
    //每页条数
    private int limit = 10;
    private long total;
    private T t;
    private List<T> data;
    private String orderBy;
    private boolean sortBy;
    private Date startTime;
    private Date endTime;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public boolean isSortBy() {
        return sortBy;
    }

    public void setSortBy(boolean sortBy) {
        this.sortBy = sortBy;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
