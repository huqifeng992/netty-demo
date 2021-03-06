package org.sang.nettydemo.test1;

/**
 * Created by huqifeng on 2018/01/24 10:02.
 */

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

public class HelloClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        /*
         * 这个地方的 必须和服务端对应上。否则无法正常解码和编码
         *
         * 解码和编码 我将会在下一张为大家详细的讲解。再次暂时不做详细的描述
         *
         * */
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        //  字符串解码
        pipeline.addLast("decoder", new StringDecoder(Charset.forName("GBK")));
        //  字符串编码
        pipeline.addLast("encoder", new StringEncoder(Charset.forName("GBK")));
        // 客户端的逻辑
        pipeline.addLast("handler", new HelloClientHandler());
    }
}
