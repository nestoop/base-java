package cn.nest.netty.protobufserver.client;

import cn.nest.protobuf.entities.MsgProto;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * Created by botter-common
 * on 17-4-16.
 */
public class PpcpClient {

    public void startClient() {

            EventLoopGroup group = new NioEventLoopGroup();

            try {
                Bootstrap bootstrap = new Bootstrap();

                bootstrap.group(group)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.SO_KEEPALIVE, true)
                        .handler(new ClientChannelHandler());

                ChannelFuture channelFuture = bootstrap.connect("127.0.0.1",9000).sync();

                channelFuture.channel().closeFuture().sync();
            } catch (Exception e) {

            } finally {
                group.shutdownGracefully();
            }
    }



    static class ClientChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new ProtobufVarint32FrameDecoder());
            socketChannel.pipeline().addLast(new ProtobufDecoder(MsgProto.Request.getDefaultInstance()));
            socketChannel.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
            socketChannel.pipeline().addLast(new ProtobufEncoder());
            socketChannel.pipeline().addLast(new ClientHandler());

        }
    }


    static class ClientHandler extends SimpleChannelInboundHandler<MsgProto.Request> {

        @Override
        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
            System.out.println("client***handlerAdded*********************");
        }

        @Override
        public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
            System.out.println("client***handlerRemoved*********************");
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            System.out.println("client***exceptionCaught*********************");
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("client***channelActive*********************");
            // send msg to server
            MsgProto.Request.Builder requestBuilder = MsgProto.Request.newBuilder();
            requestBuilder.setCode("FFFFFFFFFFFFFFF");
            requestBuilder.setType("send");
            requestBuilder.setUrl(90);

            MsgProto.Request request = requestBuilder.build();

            ctx.channel().writeAndFlush(request);
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("client***channelInactive*********************");
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            System.out.println("client***channelReadComplete*********************");
        }

        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, MsgProto.Request request) throws Exception {

        }
    }

    public static void main(String[] args) {
        new PpcpClient().startClient();
    }

}
