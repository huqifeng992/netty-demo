package org.sang.nettydemo.test4;

/**
 * Created by huqifeng on 2018/01/29 16:45.
 */
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class SelfDefineEncodeHandler extends ByteToMessageDecoder {

    private static int count = 0;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf bufferIn, List<Object> out) throws Exception {
        if (bufferIn.readableBytes() < 8) {
            return;
        }
        int beginIndex = bufferIn.readerIndex();
        byte[] head = new byte[8];
        bufferIn.readBytes(head);
        int length = new Integer(new String(head,"GBK"));
        System.out.println("decode call count="+ ++count);
        System.out.println("bufferIn.readableBytes()="+bufferIn.readableBytes());
        System.out.println("beginIndex="+beginIndex);
        if (bufferIn.readableBytes() < length) {
            bufferIn.readerIndex(beginIndex);
            return;
        }

        bufferIn.readerIndex(beginIndex + 8 + length);

        ByteBuf otherByteBufRef = bufferIn.slice(beginIndex,8 + length);

        otherByteBufRef.retain();

        out.add(otherByteBufRef);

    }
}

