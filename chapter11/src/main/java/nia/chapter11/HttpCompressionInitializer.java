package nia.chapter11;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Listing 11.4 Automatically compressing HTTP messages
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */

/**
 * HTTP压缩功能添加
 */
public class HttpCompressionInitializer extends ChannelInitializer<Channel> {
    private final boolean isClient;

    public HttpCompressionInitializer(boolean isClient) {
        this.isClient = isClient;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        // 添加压缩和解压缩Handler
        ChannelPipeline pipeline = ch.pipeline();
        if (isClient) {
            pipeline.addLast("codec", new HttpClientCodec());
            pipeline.addLast("decompressor",
            new HttpContentDecompressor());
        } else {
            pipeline.addLast("codec", new HttpServerCodec());
            pipeline.addLast("compressor",
            new HttpContentCompressor());
        }
    }
}
