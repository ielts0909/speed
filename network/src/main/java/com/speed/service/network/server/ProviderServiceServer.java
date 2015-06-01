package com.speed.service.network.server;

import com.speed.service.network.features.ServiceFeature;
import com.speed.service.network.handler.DefaultHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.List;

/**
 * service provider server
 * <p/>
 * User: gais.ch
 * Date: 15/5/14
 * Time: 下午9:36
 */
public class ProviderServiceServer implements ServiceServer {

    private DefaultHandler handler;
    private ChannelFuture f;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    public void bootstrap(final int port) throws InterruptedException {
        this.bossGroup = new NioEventLoopGroup(); // (1)
        this.workerGroup = new NioEventLoopGroup();
        final ServerBootstrap b = new ServerBootstrap(); // (2)
        b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class) // (3)
                .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                super.channelActive(ctx);
                            }

                            @Override
                            public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                                super.channelInactive(ctx);
                            }

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                super.channelRead(ctx, msg);
                                ServiceFeature feature = handler.receive(null);

                            }

                            @Override
                            public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                                super.channelReadComplete(ctx);
                            }

                            @Override
                            public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
                                super.channelRegistered(ctx);
                            }

                            @Override
                            public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
                                super.channelUnregistered(ctx);
                            }

                            @Override
                            public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
                                super.channelWritabilityChanged(ctx);
                            }

                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                super.exceptionCaught(ctx, cause);
                            }
                        });
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

        Thread runTh = new Thread(new Runnable() {
            public void run() {
                // Bind and start to accept incoming connections.
                try {
                    f = b.bind(port).sync(); // (7)
                    f.channel().closeFuture().sync();
                } catch (Exception e) {
                }
            }
        });
        runTh.start();

    }

    public void setDefaultHandler(DefaultHandler defaultHandler) {
        this.handler = defaultHandler;
    }

    /**
     * shutdown the server
     *
     * @param rightNow
     */
    public void shutdown(boolean rightNow) {
        this.workerGroup.shutdownGracefully();
        this.bossGroup.shutdownGracefully();
    }
}
