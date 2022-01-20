package nia.chapter11;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * Listing 11.1 Adding SSL/TLS support
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */

/**
 * 添加SSL/TLS支持
 */
public class SslChannelInitializer extends ChannelInitializer<Channel> {
    private final SslContext context;
    private final boolean startTls;

    public SslChannelInitializer(SslContext context,
        boolean startTls) {
        this.context = context;
        this.startTls = startTls;
    }

    /**
     * 在Channel初始化的时候添加相关的Handler
     */
    @Override
    protected void initChannel(Channel ch) throws Exception {
        // SSLEngine可以换成OpenJDK的，更加高效
        SSLEngine engine = context.newEngine(ch.alloc());
        ch.pipeline().addFirst("ssl",
            new SslHandler(engine, startTls));
    }
}
