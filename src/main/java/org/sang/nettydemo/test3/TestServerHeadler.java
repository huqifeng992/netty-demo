package org.sang.nettydemo.test3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetAddress;
import java.nio.charset.Charset;

/**
 * Created by huqifeng on 2018/01/29 11:27.
 */
public class TestServerHeadler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("通道：" + ctx.channel().remoteAddress() + "处于活跃状态！");
        String str = "欢迎：" + InetAddress.getLocalHost().getHostName() + " service!";
        ctx.writeAndFlush(str);
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf bb = (ByteBuf) msg;
        byte[] bytes = new byte[bb.readableBytes()];
        bb.readBytes(bytes);
        System.out.println("接收到客户端发来的长度为bytes："+bytes+"_"+bytes.length);
        String message = new String(bytes, Charset.forName("GBK"));
        System.out.println("接收到客户端发来的长度为message："+message+"_"+message.length());
        ctx.writeAndFlush(message);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
