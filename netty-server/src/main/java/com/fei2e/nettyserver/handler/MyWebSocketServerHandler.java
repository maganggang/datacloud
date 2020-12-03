package com.fei2e.nettyserver.handler;

import com.fei2e.nettyserver.entity.Global;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Auther: mgg
 * @Date: 2020/7/21 10:50
 * @Description:
 */
@Component
public class MyWebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
    private static final Logger logger = Logger.getLogger(WebSocketServerHandshaker.class.getName());
    private static final String URI = "webSocket";
    private WebSocketServerHandshaker handshaker;

    /**
     * 连接上服务器
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        logger.info("【handlerAdded】====>"+ctx.channel().id());
    }
    /**
     * 断开连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        logger.info("【handlerRemoved】====>"+ctx.channel().id());
    }
    /**
     * channel 通道 action 活跃的 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
// 添加
        Global.addChannel(ctx.channel());
        logger.info("客户端与服务端连接开启：" + ctx.channel().remoteAddress().toString());
    }
    /**
     * channel 通道 Inactive 不活跃的 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端关闭了通信通道并且不可以传输数据
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
// 移除
        Global.removeChannel(ctx.channel());
        System.out.println("客户端与服务端连接关闭：" + ctx.channel().remoteAddress().toString());
    }
    /**
     * 这里是保持服务器与客户端长连接  进行心跳检测 避免连接断开 卡
     * @param ctx
     * @param evt
     * @throws Exception
     */
   /* @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent stateEvent = (IdleStateEvent) evt;
            PingWebSocketFrame ping = new PingWebSocketFrame();
            switch (stateEvent.state()){
                //读空闲（服务器端）
                case READER_IDLE:
                    logger.info("【"+ctx.channel().remoteAddress()+"】读空闲（服务器端）");
                    ctx.writeAndFlush(ping);
                    break;
                //写空闲（客户端）
                case WRITER_IDLE:
                    logger.info("【"+ctx.channel().remoteAddress()+"】写空闲（客户端）");
                    ctx.writeAndFlush(ping);
                    break;
                case ALL_IDLE:
                    logger.info("【"+ctx.channel().remoteAddress()+"】读写空闲");
                    break;
            }
        }
    }*/

    /**
     * 接收客户端发送的消息 channel 通道 Read 读
     * 简而言之就是从通道中读取数据，也就是服务端接收客户端发来的数据。
     * 但是这个数据在不进行解码时它是ByteBuf类型的
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        // 传统的HTTP接入
        if (msg instanceof HttpRequest) {
            handleHttpRequest(ctx, ((HttpRequest) msg));
        // WebSocket接入
        } else if (msg instanceof WebSocketFrame) {
            handlerWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }
    /**
     * channel 通道 Read 读取 Complete 完成 在通道读取完成后会在这个方法里通知，对应可以做刷新操作 ctx.flush()
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }
    private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断是否关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            logger.info("[关闭]");
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 判断是否ping消息
        if (frame instanceof PingWebSocketFrame) {
            logger.info("[ping]");
            PongWebSocketFrame pong=new PongWebSocketFrame(frame.content().retain());
            ctx.channel().writeAndFlush(pong);
            return;
        }
        if(frame instanceof  PongWebSocketFrame){
            logger.info("[pong]");
            PingWebSocketFrame ping=new PingWebSocketFrame(frame.content().retain());
            ctx.channel().writeAndFlush(ping);
            return;
        }
        // 本例程仅支持文本消息，不支持二进制消息
        if (!(frame instanceof TextWebSocketFrame)) {
           logger.info("本例程仅支持文本消息，不支持二进制消息");
            throw new UnsupportedOperationException(
                    String.format("%s frame types not supported", frame.getClass().getName()));
        }
// 返回应答消息
        String request = ((TextWebSocketFrame) frame).text();
        System.out.println("服务端收到：" + request);
        if (logger.isLoggable(Level.FINE)) {
            logger.info(String.format("%s received %s", ctx.channel(), request));
        }
        TextWebSocketFrame tws = new TextWebSocketFrame(new Date().toString() + ctx.channel().id() + "：" + request);
// 群发
        Global.send2All(tws);
// 返回【谁发的发给谁】
// ctx.channel().writeAndFlush(tws);
    }
    /**
     * 唯一的一次http请求，用于创建websocket
     * */
    private void handleHttpRequest(ChannelHandlerContext ctx, HttpRequest req) {
        // 如果HTTP解码失败，返回HHTP异常  //要求Upgrade为websocket，过滤掉get/Post
        if (!req.decoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {
            //若不是websocket方式，则创建BAD_REQUEST的req，返回给客户端
            sendHttpResponse(ctx, (FullHttpRequest)req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        //可以获取msg的uri来判断
        String uri=req.uri();
        System.out.println(uri);
       /* if(!uri.substring(1).equals(URI)){
            ctx.close();
        }*/
        //获取url后置参数
        HttpMethod method=req.method();
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(uri);
        Map<String, List<String>> parameters = queryStringDecoder.parameters();
        System.out.println("firstConnect"+parameters.get("request").get(0));
        if(method== HttpMethod.GET&&"/webssss".equals(uri)){
            ctx.attr(AttributeKey.valueOf("type")).set("died");
        }else if(method== HttpMethod.GET&&"/websocket".equals(uri)){
            ctx.attr(AttributeKey.valueOf("type")).set("live");
        }
// 构造握手响应返回，本机测试
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                "ws://"+req.headers().get(HttpHeaders.Names.HOST)+uri, null, false);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        }  //进行连接
        handshaker.handshake(ctx.channel(), (FullHttpRequest) req);
        //可以做其他处理
    }
    /**
     * 拒绝不合法的请求，并返回错误信息
     * */
    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        // 返回应答给客户端
        if (res.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!HttpHeaders.isKeepAlive(req) || res.getStatus().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }
    /**
     * exception 异常 Caught 抓住 抓住异常，当发生异常的时候，可以做一些相应的处理，比如打印日志、关闭链接
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)  {
        cause.printStackTrace();
        ctx.close();
    }
}
