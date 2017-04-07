package cn.nest.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by botter
 * on 17-4-6.
 */
public class ClientHandler extends SimpleChannelInboundHandler<Object> {

    private  ByteBuf sendMsg;

    private ConnectInfo connectInfo;

    public static volatile  List<String[]> alList = Arrays.asList();

    public static volatile List<String> list = Arrays.asList();


    private static Map<String, String> map = new ConcurrentHashMap<>();

    public ClientHandler() {
        byte[] req = "client msg".getBytes();
        sendMsg = Unpooled.buffer(req.length);
        sendMsg.writeBytes(req);
        connectInfo = new ConnectInfo("Client");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];

        buf.readBytes(req);
        String body = new String(req, "UTF-8");

//        System.out.println("client revice date :" + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss:SSS") + " body :" + body);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(sendMsg);
        connectInfo.setStartTime();
        connectInfo.setSize(sendMsg.readableBytes());
//       System.out.println("client send msg time :" + System.currentTimeMillis());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("client channel close time :" + System.currentTimeMillis());
        ctx.close();
        connectInfo.setEndTime();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("client close time :" + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss:SSS"));
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
