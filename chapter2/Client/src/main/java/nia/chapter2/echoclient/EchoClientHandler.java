package nia.chapter2.echoclient;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Listing 2.3 ChannelHandler for the client
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
@Sharable // 类的实例可以被多个Channel共享
public class EchoClientHandler
    extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) { // 与服务器的连接已经建立之后
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!",
                CharsetUtil.UTF_8));
    }

    @Override
    /**
     * 从服务器来的数据可能是分块的，即使服务器的一条消息，该方法也可能会被调用多次
     * TCP保证接受到的数据顺序是服务器的发送数据
     *
     * channelRead0方法是ChannelInboundHandlerAdapter类read和readComplete方法的结合，
     * 该方法会自动释放保存该消息ByteBuf的内存引用
     */
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) { // 从服务器收到一条消息
        System.out.println(
                "Client received: " + in.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
        Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
