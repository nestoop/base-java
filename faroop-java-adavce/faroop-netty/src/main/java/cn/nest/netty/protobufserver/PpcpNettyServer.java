package cn.nest.netty.protobufserver;

import cn.nest.netty.protobufserver.handler.ProtoBufMessageHandler;
import cn.nest.protobuf.entities.MsgProto;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * Created by botter
 * on 17-4-16.
 */
public class PpcpNettyServer {

    public void startPpcpServer() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 10240)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new ServerChannelHandler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.bind(8999).sync();

            channelFuture.channel().closeFuture().sync();

        } catch (Exception e) {

        } finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    static class ServerChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {

            socketChannel.pipeline().addLast(new ProtobufVarint32FrameDecoder());
            socketChannel.pipeline().addLast(new ProtobufDecoder(MsgProto.Request.getDefaultInstance()));
            socketChannel.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
            socketChannel.pipeline().addLast(new ProtobufEncoder());
            socketChannel.pipeline().addLast(new IdleStateHandler(3, 4, 6, TimeUnit.SECONDS));

            socketChannel.pipeline().addLast(new ProtoBufMessageHandler());
        }
    }

    public static void main(String[] args) {
        new PpcpNettyServer().startPpcpServer();
    }
}
