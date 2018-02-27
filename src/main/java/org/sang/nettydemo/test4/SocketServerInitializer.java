package org.sang.nettydemo.test4;

/**
 * Created by huqifeng on 2018/01/29 16:45.
 */
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


public class SocketServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new SelfDefineEncodeHandler());
        pipeline.addLast(new BusinessServerHandler());
    }
}
