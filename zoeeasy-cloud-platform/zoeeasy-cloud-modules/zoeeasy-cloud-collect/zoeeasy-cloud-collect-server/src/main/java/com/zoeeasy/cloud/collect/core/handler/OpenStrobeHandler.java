package com.zoeeasy.cloud.collect.core.handler;

import com.zoeeasy.cloud.collect.msgbody.result.OpenStrobeResultBody;
import com.zoeeasy.cloud.collect.packets.CollectPacket;
import lombok.extern.slf4j.Slf4j;
import org.tio.core.ChannelContext;
import org.tio.utils.json.Json;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * @Date: 2019/1/12
 * @author: wh
 */
@Slf4j
public class OpenStrobeHandler extends AbstractBizHandler<OpenStrobeResultBody> {

    public OpenStrobeHandler() {
    }

    @Override
    public Class<OpenStrobeResultBody> bodyClass() {
        return OpenStrobeResultBody.class;
    }

    @Override
    public Object handler(CollectPacket packet, OpenStrobeResultBody bsBody, ChannelContext channelContext) throws Exception {
        String str = Json.toJson(bsBody);
        log.info("[OpenStrobeHandler.handler]收到请求远程开闸返回消息：--{}--", str);
        WriteLock writeLock = channelContext.groupContext.connections.writeLock();
        Condition condition = writeLock.newCondition();
        writeLock.lock();
        try {
            String attributeKey = channelContext.userid + bsBody.getService() + bsBody.getGateCode();
            channelContext.setAttribute(attributeKey, str);
            condition.signal();
        } catch (Throwable throwable) {
            log.error(throwable.getMessage());
        } finally {
            writeLock.unlock();
        }
        return null;
    }
}
