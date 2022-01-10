package nia.chapter10;

import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * Listing 10.10 CombinedChannelDuplexHandler<I,O>
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */

/**
 * 组合式的消息编码和解码器类，对应前面的单个、复合、组合编码器和解码器功能
 */
public class CombinedByteCharCodec extends
    CombinedChannelDuplexHandler<ByteToCharDecoder, CharToByteEncoder> {
    public CombinedByteCharCodec() {
        super(new ByteToCharDecoder(), new CharToByteEncoder());
    }
}
