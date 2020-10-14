package com.fei2e.filemanage.entity;

/**
 * @ClassName BaseResult
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/9/15 11:48
 * @Version 1.0
 **/
public class BaseResult<T> {
    private Integer status=200;
    private String message;
    private T data;
    public BaseResult(){}
    public BaseResult(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
