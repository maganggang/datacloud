package com.fei2e.nettyserver.entity;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @ClassName GlobalUserUtil
 * @DescripTion TODO
 * @Author dell
 * @Date 2020/12/3 17:57
 * @Version 1.0
 **/
public class GlobalUserUtil {
    //保存全局的  连接上服务器的客户
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor
            .INSTANCE);
}
