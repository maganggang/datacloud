package com.fei2e.nettyserver.entity;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 存储访问的channle，channelGroup的原型是set集合，
 * 保证channle的唯一，如需根据参数标注存储，可以使用currentHashMap来存储。
 * @Auther: mgg
 * @Date: 2020/7/21 10:50
 * @Description:
 */
public class Global {
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private  static ConcurrentMap<String, ChannelId> ChannelMap=new ConcurrentHashMap();
    public  static void addChannel(Channel channel){
        group.add(channel);
        ChannelMap.put(channel.id().asShortText(),channel.id());
    }
    public static void removeChannel(Channel channel){
        group.remove(channel);
        ChannelMap.remove(channel.id().asShortText());
    }
    public static  Channel findChannel(String id){
        return group.find(ChannelMap.get(id));
    }
    public static void send2All(TextWebSocketFrame tws){
        group.writeAndFlush(tws);
    }
}
