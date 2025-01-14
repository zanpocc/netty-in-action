package nia.chapter6;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Listing 6.2 Using SimpleChannelInboundHandler
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
@Sharable
public class SimpleDiscardHandler
    extends SimpleChannelInboundHandler<Object> {
    /**
     * 继承该接口可以不用显式释放资源
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx,
        Object msg) {
        // No need to do anything special
    }
}
