package cn.nest.netty.protobufserver.handler;

import cn.nest.protobuf.entities.MsgProto;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by botter-common
 * on 17-4-16.
 */
public class ProtoBufMessageHandler extends SimpleChannelInboundHandler<MsgProto.Request> {
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server***handlerAdded*********************");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server***handlerRemoved*********************");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("server***exceptionCaught*********************");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server***channelActive*********************");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server***channelInactive*********************");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server***channelReadComplete*********************");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MsgProto.Request msg) throws Exception {
        //msg to byte buf
        ByteBuf byteBuf = Unpooled.copiedBuffer(msg.toByteArray());

        System.out.println(msg.toString());
    }
}
