package cn.nest.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by botter
 * on 17-4-5.
 */

public class ServerHandler extends SimpleChannelInboundHandler<Object> {

    private final static AtomicLong atomicLong = new AtomicLong(1);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        atomicLong.incrementAndGet();
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];

        buf.readBytes(req);
        String body = new String(req, "UTF-8");

        System.out.println("server revice date :" + System.currentTimeMillis() + " body :" + body  + " size :" + body.getBytes().length);

//        ByteBuf responseMsg = Unpooled.copiedBuffer("respMessage".getBytes());
//
//        ctx.write(responseMsg);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("server close time :" + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss:SSS"));
//        connectInfo.setEndTime();
//        System.out.println("[Server] startTime:" + connectInfo.getStartTime() + " endTime :" + connectInfo.getEndTime() + " substract = " + (connectInfo.getEndTime()- connectInfo.getStartTime()));
        System.out.println("**********************         =" + atomicLong.get());
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        connectInfo.setEndTime();
//        System.out.println("[Server] startTime:" + connectInfo.getStartTime() + " endTime :" + connectInfo.getEndTime() + " substract = " + (connectInfo.getEndTime()- connectInfo.getStartTime()));
        ctx.close();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
