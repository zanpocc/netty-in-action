package nia.chapter10;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * Listing 10.3 Class IntegerToStringDecoder
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */

/**
 * 消息类型转换类：MessageToMessageDecoder，转换消息的格式
 */
public class IntegerToStringDecoder extends
    MessageToMessageDecoder<Integer> {
    @Override
    public void decode(ChannelHandlerContext ctx, Integer msg,
        List<Object> out) throws Exception {
        out.add(String.valueOf(msg));
    }
}

