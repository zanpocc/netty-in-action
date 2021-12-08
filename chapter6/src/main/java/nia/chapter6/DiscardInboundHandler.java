package nia.chapter6;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Listing 6.3 Consuming and releasing an inbound message
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
@Sharable
public class DiscardInboundHandler extends ChannelInboundHandlerAdapter {
    /**
     * ChannelInboundHandlerAdapter的channelRead需要自己手动释放消息，而SimpleChannelInboundHandler就可以不需要显式释放
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // ⚠️：将消息转发到下一个Handle
        ctx.fireChannelRead(msg);
        ReferenceCountUtil.release(msg);
    }
}
