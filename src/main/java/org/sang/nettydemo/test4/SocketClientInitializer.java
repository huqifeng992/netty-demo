package org.sang.nettydemo.test4;

/**
 * Created by huqifeng on 2018/01/29 16:46.
 */
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

public class SocketClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new StringEncoder(Charset.forName("GBK")));
        pipeline.addLast(new PackageEncodeHandler());
        pipeline.addLast(new SocketClientHandler());
    }
}
