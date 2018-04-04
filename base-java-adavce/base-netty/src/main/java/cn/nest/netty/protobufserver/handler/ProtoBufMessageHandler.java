package cn.nest.netty.protobufserver.handler;

import cn.nest.netty.msg.MsgProto;
import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by botter-common
 * on 17-4-16.
 */
public class ProtoBufMessageHandler extends SimpleChannelInboundHandler<MsgProto.Request> {

    private static volatile Map<String, Channel> seqAndChannelMap = new ConcurrentHashMap<>();

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
        seqAndChannelMap.put("DD", ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server***channelInactive*********************");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server***channelReadComplete*********************");
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MsgProto.Request msg) throws Exception {
        //msg to byte buf
        System.out.println("[server] msg = " + msg.toString());
        Channel channel = seqAndChannelMap.get("DD");
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println(JSON.toJSONString(channel));
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        if (channel !=null && channel.isActive()) {
            System.out.println("seq dd channel is active "  + channel.isActive());
            ByteBuf byteBuf = Unpooled.copiedBuffer(msg.toByteArray());
            for (int i = 0; i< 10 ;i ++) {
                System.out.println("server.......");
                channel.writeAndFlush(byteBuf);
                Thread.sleep(1000);
            }
            System.out.println(msg.toString());
            System.out.println("after send msg channel is active "  + channel.isActive());

        } else {

            System.out.println("channel is active  "  + channel.isActive());
        }

    }
}
