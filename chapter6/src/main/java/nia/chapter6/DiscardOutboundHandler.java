package nia.chapter6;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;

/**
 * Listing 6.4 Discarding and releasing outbound data
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
@Sharable
public class DiscardOutboundHandler
    extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx,
        Object msg, ChannelPromise promise) {
        // 当我们丢弃了一个出站消息后，应当对其释放，并通知ChannelPromise，消息已被处理
        // 或者使用SimpleChannelInBoundHandler进行默认丢弃处理
        ReferenceCountUtil.release(msg);
        promise.setSuccess();
    }
}

