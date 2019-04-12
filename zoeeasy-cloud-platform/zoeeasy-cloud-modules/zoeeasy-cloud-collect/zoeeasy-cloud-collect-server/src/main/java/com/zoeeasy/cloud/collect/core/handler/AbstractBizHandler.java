package com.zoeeasy.cloud.collect.core.handler;

import com.zoeeasy.cloud.collect.core.BizHandlerInterface;
import com.zoeeasy.cloud.collect.cst.CollectConst;
import com.zoeeasy.cloud.collect.msgbody.request.BaseBody;
import com.zoeeasy.cloud.collect.packets.CollectPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.utils.json.Json;

/**
 * @Date: 2019-03-03
 * @author: wh
 */
public abstract class AbstractBizHandler<T extends BaseBody> implements BizHandlerInterface {
    private static Logger log = LoggerFactory.getLogger(AbstractBizHandler.class);

    public AbstractBizHandler() {
    }

    /**
     * 不同业务的抽象类
     *
     * @return
     */
    public abstract Class<T> bodyClass();

    @Override
    public Object handler(CollectPacket packet, ChannelContext channelContext) throws Exception {
        String jsonStr = null;
        T bsBody = null;
        if (packet.getBody() != null) {
            jsonStr = new String(packet.getBody(), CollectConst.CHARSET);
            bsBody = Json.toBean(jsonStr, bodyClass());
        }

        return handler(packet, bsBody, channelContext);
    }

    /**
     * 业务处理
     *
     * @param packet
     * @param bsBody
     * @param channelContext
     * @return
     * @throws Exception
     */
    public abstract Object handler(CollectPacket packet, T bsBody, ChannelContext channelContext) throws Exception;

}
