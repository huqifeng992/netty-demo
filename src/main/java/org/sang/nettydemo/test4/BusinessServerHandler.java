package org.sang.nettydemo.test4;

/**
 * Created by huqifeng on 2018/01/29 16:46.
 */
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class BusinessServerHandler extends ChannelInboundHandlerAdapter {

    private static int count = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;

        byte[] head = new byte[8];
        buf.readBytes(head);
        String headString = new String(head);
        System.out.println(headString);

        byte[] body = new byte[8];
        buf.readBytes(body);
        String bodyString = new String(body,"GBK");
        System.out.println(bodyString);
        System.out.println("BusinessServerHandler call count="+ ++count);
    }
}
