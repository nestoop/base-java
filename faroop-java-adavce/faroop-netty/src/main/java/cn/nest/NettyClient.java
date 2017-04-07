package cn.nest;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by botter
 * on 17-4-6.
 */

public class NettyClient {


    public  void client() {

        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new ClientHandler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.connect("localhost",9000).sync();

            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {

        } finally {
            group.shutdownGracefully();
        }

    }

}
