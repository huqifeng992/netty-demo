package org.sang.nettydemo.test4;

/**
 * <p>
 *     处理半包粘包问题
 * </p>
 *
 * @Author huqifeng
 * @Date 2018/01/30 10:09
 */
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class PackageEncodeHandler extends ByteToMessageDecoder {

    private static final int headLength = 8;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf bufferIn, List<Object> out) throws Exception {
        // 如果接收到的字节还不到8个字节,也即是连消息长度字段中的内容都不完整的,直接return
        if (bufferIn.readableBytes() < headLength) {
            return;
        }
        // 对于拆包这种场景,由于还未读取到完整的消息,bufferIn.readableBytes() 会小于length,
        // 并重置bufferIn的readerIndex为0,然后退出,ByteToMessageDecoder会乖乖的等待下个包的到来
        // 由于第一次调用中readerIndex被重置为0,那么decode方法被调用第二次的时候,beginIndex还是为0的
        int beginIndex = bufferIn.readerIndex();
        byte[] head = new byte[headLength];
        bufferIn.readBytes(head);
        int length = new Integer(new String(head,"GBK"));
        if (bufferIn.readableBytes() < length) {
            bufferIn.readerIndex(beginIndex);
            return;
        }
        // 将readerIndex设置为最大。首先代码能执行到这里,针对拆包这种场景而言,已经是读取到一条有效完整的消息了。
        // 这个时候需要通知ByteToMessageDecoder类,bufferIn中的数据已经读取完毕了,不要再调用decode方法了。
        // ByteToMessageDecoder类的底层会根据bufferIn.isReadable()方法来判断是否读取完毕。
        // 只有将readerIndex设置为最大,bufferIn.isReadable()方法才会返回false。
        bufferIn.readerIndex(beginIndex + headLength + length);
        // 当decode方法执行完后,会释放bufferIn这个缓冲区,如果将执行完释放操作的bufferIn传递给下个处理器的话,
        // 一旦下个处理器调用bufferIn的读或者写的方法时,会立刻报出IllegalReferenceCountException异常的。
        // 因此slice操作后,必须加上一个retain操作,让bufferIn的引用计数器加1,这样ByteToMessageDecoder会刀下留人,先不释放bufferIn。
        ByteBuf otherByteBufRef = bufferIn.slice(beginIndex,headLength + length);
        otherByteBufRef.retain();
        out.add(otherByteBufRef);

    }
}
