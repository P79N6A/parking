package com.zoeeasy.cloud.collect.core;

import com.zoeeasy.cloud.collect.cst.CollectConst;
import com.zoeeasy.cloud.collect.packets.CollectPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioHandler;

import java.nio.ByteBuffer;

/**
 * @author wf
 */
public abstract class AbstractCollectServerAioHandler implements ServerAioHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCollectServerAioHandler.class);

    /**
     * 显示接收的数据包
     *
     * @param packet 接收的数据包
     */
    public abstract void showPacket(CollectPacket packet);

    @Override
    public CollectPacket decode(ByteBuffer buffer, int limit, int position, int readableLength, ChannelContext channelContext) throws AioDecodeException {
        // 不够消息体长度(剩下的buffer组不了消息体)
        if (readableLength < CollectConst.MESSAGE_HEAD_LENGTH) {
            return null;
        }

        CollectPacket collectPacket = new CollectPacket();
        //包自带前四字节表示包长度
        collectPacket.setLen(buffer.getInt());

        //报文实际长度：四个字节表示
        int msgLength = buffer.getInt();
        if (readableLength < msgLength + CollectConst.MESSAGE_HEAD_LENGTH) {
            return null;
        }

        if (readableLength > 0) {
            byte[] dst = new byte[msgLength];
            buffer.get(dst);
            collectPacket.setBody(dst);
        }
        return collectPacket;
    }

    @Override
    public ByteBuffer encode(Packet packet, GroupContext groupContext, ChannelContext channelContext) {
        CollectPacket collectPacket = (CollectPacket) packet;
        byte[] body = collectPacket.getBody();
        int bodyLen = 0;
        if (body != null) {
            bodyLen = body.length;
        }
        ByteBuffer buffer = ByteBuffer.allocate(bodyLen + CollectConst.MESSAGE_HEAD_LENGTH);
        //设置字节序
        buffer.order(groupContext.getByteOrder());
        buffer.putInt(bodyLen);
        //写入消息体
        if (body != null) {
            buffer.put(body);
        }
        return buffer;
    }
}
