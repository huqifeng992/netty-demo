package org.sang.nettydemo.test4;

/**
 * Created by huqifeng on 2018/01/29 16:47.
 */
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SocketClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        System.out.println("Server say : " + new String(bytes, "GBK"));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //正常
//        UnpooledByteBufAllocator allocator = new UnpooledByteBufAllocator(false);
//        ByteBuf buffer = allocator.buffer(20);
//        buffer.writeBytes("00000008".getBytes());
//        buffer.writeBytes("123456你".getBytes());
//        ctx.writeAndFlush(buffer);
        //String解析
        String str = "00000135INQ_100112345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345胡00000135INQ_100112345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345";
        ctx.writeAndFlush(str);
        Thread.sleep(10000);
        ctx.writeAndFlush("胡");

        // 半包
//        UnpooledByteBufAllocator allocator = new UnpooledByteBufAllocator(false);
//        ByteBuf buffer = allocator.buffer(20);
//        buffer.writeBytes("00001600".getBytes());
//        String longMsgBody = "";
//        for (int i = 0; i < 399; i++) {
//            longMsgBody = longMsgBody + "body";
//        }
//        buffer.writeBytes(longMsgBody.getBytes());
//        ctx.writeAndFlush(buffer);
//        Thread.sleep(10000);
//        ctx.writeAndFlush(Unpooled.copiedBuffer("body".getBytes()));
        //粘包
//        for (int i = 0; i < 20; i++) {
//            UnpooledByteBufAllocator allocator = new UnpooledByteBufAllocator(false);
//            ByteBuf buffer = allocator.buffer(20);
//            buffer.writeBytes("00000008".getBytes());
//            buffer.writeBytes("12345678".getBytes());
//            ctx.writeAndFlush(buffer);
//        }
    }
}
