package nia.chapter10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * Listing 10.2 Class ToIntegerDecoder2 extends ReplayingDecoder
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */

/**
 * 使用ReplayingDecoder类的话可以简化操作，可以不对流中的可读字节进行判断
 *
 * 如果可读长度不足4字节，会抛出一个ERROR给基类
 *
 * 缺点：会稍慢于ByteToMessageDecoder类
 */
public class ToIntegerDecoder2 extends ReplayingDecoder<Void> {

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in,
        List<Object> out) throws Exception {
        out.add(in.readInt());
    }
}

