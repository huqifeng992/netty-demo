package org.sang.nettydemo.test3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * Created by huqifeng on 2018/01/29 11:17.
 */
public class TestServer {

    private static final int port = 7878;

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap sb = new ServerBootstrap();
        sb.group(bossGroup,workerGroup);
        sb.channel(NioServerSocketChannel.class);
        sb.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
                ch.pipeline().addLast(new TestServerHeadler());
            }
        });
        ChannelFuture f = sb.bind(port).sync();
        f.channel().closeFuture().sync();
    }

}
