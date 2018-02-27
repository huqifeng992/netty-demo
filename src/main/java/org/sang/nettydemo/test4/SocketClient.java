package org.sang.nettydemo.test4;

/**
 * Created by huqifeng on 2018/01/29 16:46.
 */
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class SocketClient {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .handler(new SocketClientInitializer());

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6867).sync();
            channelFuture.channel().closeFuture().sync();
        }
        finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
