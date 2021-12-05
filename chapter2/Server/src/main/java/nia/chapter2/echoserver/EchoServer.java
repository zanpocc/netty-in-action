package nia.chapter2.echoserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Listing 2.2 EchoServer class
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args)
        throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: " + EchoServer.class.getSimpleName() +
                " <port>"
            );
            return;
        }
        int port = Integer.parseInt(args[0]);
        new EchoServer(port).start();
    }

    public void start() throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        // 创建一个EventLoopGroup用于事件处理，包括接受新连接以及读写数据
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 启动Netty服务器,ServerBootstrap用于引导和绑定服务器
            ServerBootstrap b = new ServerBootstrap();
            // 配置属性
            b.group(group)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        // 每个channel都有一个pipeline，是一个链表结构，每个handle处理完后就会传递到下一个handle
                        ch.pipeline().addLast(serverHandler);
                    }
                });

            // 阻塞绑定
            ChannelFuture f = b.bind().sync();
            System.out.println(EchoServer.class.getName() +
                " started and listening for connections on " + f.channel().localAddress());

            // 阻塞等Channel关闭后退出
            f.channel().closeFuture().sync();
        } finally {
            // 关闭EventLoop，释放所有资源
            group.shutdownGracefully().sync();
        }
    }
}
