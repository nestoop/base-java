package cn.nest.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.*;

/**
 * Created by botter
 * on 17-4-5.
 */
public class NettyServer {

    public static void main(String[] args) {


        EventLoopGroup bossGroup = new NioEventLoopGroup();

        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new ServerHandler());
                        }
                    });

            ChannelFuture channelFuture = bootstrap.bind(9000).sync();

            channelFuture.channel().closeFuture().sync();

        } catch (Exception e) {

        } finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }

    public static void createCSVFile(ConnectInfo connectInfo) {
        BufferedWriter csvFileOutputStream = null;
        try {
            File csvFile = new File("/home/botter-common/connectInfo.csv");
            // UTF-8使正确读取分隔符","
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), "UTF-8"), 1024);
            // 写入文件内容
            csvFileOutputStream.write((connectInfo.getEndTime()-connectInfo.getStartTime())+ "");
            csvFileOutputStream.newLine();

            csvFileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvFileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
