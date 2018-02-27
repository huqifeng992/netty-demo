package org.sang.nettydemo.test3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.Charset;

/**
 * Created by huqifeng on 2018/01/29 11:52.
 */
public class TestClientHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client active ");
        String str = "0000000812345678";
        ctx.writeAndFlush(str);
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            ByteBuf bb = (ByteBuf) msg;
            byte[] bytes = new byte[bb.readableBytes()];
            bb.readBytes(bytes);
            System.out.println("接收到服务端发来的长度为bytes：" + bytes + "_" + bytes.length);
            String message = new String(bytes, Charset.forName("GBK"));
            System.out.println("接收到服务端发来的长度为message：" + message + "_" + message.length());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ReferenceCountUtil.release(msg);// 只读的时候需要释放资源
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Client close ");
        super.exceptionCaught(ctx, cause);
    }
}
