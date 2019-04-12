package com.zoeeasy.cloud.collect.core;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.zoeeasy.cloud.collect.core.handler.*;
import com.zoeeasy.cloud.collect.cst.CollectConst;
import com.zoeeasy.cloud.collect.enums.BizEnum;
import com.zoeeasy.cloud.collect.packets.CollectPacket;
import lombok.extern.slf4j.Slf4j;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioHandler;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wf
 */
@Slf4j
public class CollectServerAioHandler extends AbstractCollectServerAioHandler implements ServerAioHandler {

    private static Map<String, AbstractBizHandler<?>> handlerMap = new HashMap<>();

    static {
        handlerMap.put(BizEnum.HEART_BEAT.getComment(), new HeartBeatHandler());
        handlerMap.put(BizEnum.CHECK_KEY.getComment(), new CheckKeyHandler());
        handlerMap.put(BizEnum.PAYMENT_NOTIFY.getComment(), new PaymentNotifyHandler());
        handlerMap.put(BizEnum.QUERY_PRICE.getComment(), new QueryPriceHandler());
        handlerMap.put(BizEnum.OPEN_STROBE.getComment(), new OpenStrobeHandler());
    }

    /**
     * 处理消息
     */
    @Override
    public void handler(Packet packet, ChannelContext channelContext) throws Exception {
        CollectPacket collectPacket = (CollectPacket) packet;
        if (collectPacket.getBody() == null) {
            return;
        }
        byte[] body = collectPacket.getBody();
        String str = new String(body, Charset.forName(CollectConst.CHARSET));
        JSONObject jsonObject;
        try {
            jsonObject = JSONObject.parseObject(str);
        } catch (JSONException jsonex) {
            log.error("JSONObject序列化TCP客户端数据异常" + str);
            return;
        }
        //不同接口处理
        String service = (String) jsonObject.get(CollectConst.BIZ_SERVICE);
        AbstractBizHandler<?> showcaseBsHandler = handlerMap.get(service);
        if (showcaseBsHandler == null) {
            log.error("{}, 找不到处理接口类，service:{}", channelContext, service);
            return;
        }
        showcaseBsHandler.handler(collectPacket, channelContext);
        return;
    }

    @Override
    public void showPacket(CollectPacket packet) {
        log.info("[TioServerAioHandler.showPacket]接收数据：{}", packet.toString());
    }
}
