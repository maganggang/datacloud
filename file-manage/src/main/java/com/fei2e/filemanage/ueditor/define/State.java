package com.fei2e.filemanage.ueditor.define;

/**
 * @ClassName State
 * @DescripTion 处理状态接口
 * @Author dell
 * @Date 2020/9/14 14:59
 * @Version 1.0
 **/
public interface State {

    public boolean isSuccess();

    public void putInfo(String name, String val);

    public void putInfo(String name, long val);

    public String toJSONString();
}
