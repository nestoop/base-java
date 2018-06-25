package cn.nest.netty;

import cn.nest.util.StringUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.mqtt.*;

/**
 * on 17-4-5.
 * @author botter
 */
public class ServerHandler extends SimpleChannelInboundHandler<MqttMessage> {

    protected boolean closeSession;
    protected String clientId;
    protected int version;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MqttMessage msg) throws Exception {
        
        //message invalide
        if (msg.decoderResult().isFailure()) {
            Throwable cause = msg.decoderResult().cause();
            System.out.println("message decode is failure");
            if (cause instanceof MqttIdentifierRejectedException) {
                ctx.close();
                return;
            } else if (cause instanceof MqttUnacceptableProtocolVersionException) {
                
            }
        }
        
        //do messageType 
        
        switch (msg.fixedHeader().messageType()) {
            case CONNECT:
                onConnect(ctx, (MqttConnectMessage) msg);
                break;
            case PUBLISH:
                onPublish(ctx, (MqttPublishMessage) msg);
                break;
            case PUBACK:
                onPubAck(ctx, msg);
                break;
            case PUBREC:
                onPubRec(ctx, msg);
                break;
            case PUBREL:
                onPubRel(ctx, msg);
                break;
            case PUBCOMP:
                onPubComp(ctx, msg);
                break;
            case SUBSCRIBE:
                onSubscribe(ctx, (MqttSubscribeMessage) msg);
                break;
            case UNSUBSCRIBE:
                onUnsubscribe(ctx, (MqttUnsubscribeMessage) msg);
                break;
            case PINGREQ:
                onPingReq(ctx);
                break;
            case DISCONNECT:
                onDisconnect(ctx);
                break;
        }
    }

    /***
     * do connect
     * 
     */
    protected void onConnect(ChannelHandlerContext ctx, MqttConnectMessage msg) {
        System.out.println("[Handler] onconnect ");
        this.version = msg.variableHeader().version();
        this.clientId = msg.payload().clientIdentifier();
        this.closeSession = msg.variableHeader().isCleanSession();

        if (StringUtil.isEmpty(clientId)) {

        }
    }

    /***
     * do publish
     * 
     */
    protected void onPublish(ChannelHandlerContext ctx, MqttPublishMessage msg) {
        System.out.println("[Handler] onPublish ");
    }

    /***
     * do subscribe
     */
    protected void onSubscribe(ChannelHandlerContext ctx, MqttSubscribeMessage msg) {
        System.out.println("[Handler] onSubscribe ");
    }


    /***
     * do unsubscribe
     */
    protected void onUnsubscribe(ChannelHandlerContext ctx, MqttUnsubscribeMessage msg) {
        System.out.println("[Handler] onUnsubscribe ");
    }

    /**
     * do publish ack
     * 
     */
    protected void onPubAck(ChannelHandlerContext ctx, MqttMessage msg) {
        System.out.println("[Handler] onPubAck ");
    }

    /***
     * publish receive msg
     * @param ctx
     * @param msg
     */
    protected void onPubRec(ChannelHandlerContext ctx, MqttMessage msg) {
        System.out.println("[Handler] onPubRec ");
    }

    protected void onPubRel(ChannelHandlerContext ctx, MqttMessage msg) {
        System.out.println("[Handler] onPubRel ");
    }

    /***
     * unkown what message ? to do
     * @param ctx
     * @param msg
     */
    protected void onPubComp(ChannelHandlerContext ctx, MqttMessage msg) {
        System.out.println("[Handler] onPubComp ");
    }

    /**
     * send ping msg
     * @param ctx
     */
    protected void onPingReq(ChannelHandlerContext ctx) {
        System.out.println("[Handler] onPingReq ");
    }

    /***
     * disconnet
     * @param ctx
     */
    protected void onDisconnect(ChannelHandlerContext ctx) {
        System.out.println("[Handler] onDisconnect ");
    }
}
