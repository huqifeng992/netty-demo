package org.sang.nettydemo.test2;

/**
 * Created by huqifeng on 2018/01/26 17:41.
 */


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Handles a server-side channel.
 * @author yi.gao
 *
 */
public class ServerHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf buf = (ByteBuf)msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        String message = new String(bytes,"UTF-8");
        System.out.println("服务端收到的消息： " + message);
        System.out.println("byte.length: "+bytes.length);
        System.out.println("message.length"+message.length());

        //向客户端写数据
        String response = "hello你";
        ByteBuf buffer = Unpooled.copiedBuffer(response.getBytes());
        ctx.write(buffer);//写入缓冲数组
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete...");
        ctx.flush();//将缓冲区数据写入SocketChannel
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        System.out.println("exceptionCaught...");
    }





}


