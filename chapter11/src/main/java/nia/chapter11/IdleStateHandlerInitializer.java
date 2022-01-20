package nia.chapter11;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * Listing 11.7 Sending heartbeats
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */

/**
 * Websocket心跳
 */
public class IdleStateHandlerInitializer extends ChannelInitializer<Channel>
    {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(
                new IdleStateHandler(0, 0, 60, TimeUnit.SECONDS)); // 心跳Handler，触发时发送IdleStateEvent事件
        pipeline.addLast(new HeartbeatHandler());
    }

    /**
     * 发送心跳消息
     */
    public static final class HeartbeatHandler
        extends ChannelInboundHandlerAdapter {
        private static final ByteBuf HEARTBEAT_SEQUENCE =
                Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(
                "HEARTBEAT", CharsetUtil.ISO_8859_1));
        @Override
        public void userEventTriggered(ChannelHandlerContext ctx,
            Object evt) throws Exception {
            if (evt instanceof IdleStateEvent) { // 发送心跳消息
                ctx.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate())
                     .addListener(
                         ChannelFutureListener.CLOSE_ON_FAILURE); // 60s没收到关闭连接
            } else {
                super.userEventTriggered(ctx, evt);
            }
        }
    }
}
