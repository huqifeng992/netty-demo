package org.sang.nettydemo.test3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * Created by huqifeng on 2018/01/29 11:39.
 */
public class TestClient {

    static final String ip = "127.0.0.1";
    static final int port = 7878;

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group);
        b.channel(NioSocketChannel.class);
        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
                ch.pipeline().addLast(new TestClientHandler());
            }
        });
        ChannelFuture future = b.connect(ip, port).sync();
        future.channel().closeFuture().sync();
    }
}
