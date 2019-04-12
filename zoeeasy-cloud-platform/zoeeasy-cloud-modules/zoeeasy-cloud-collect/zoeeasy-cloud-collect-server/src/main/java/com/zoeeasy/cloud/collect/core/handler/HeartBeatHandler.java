package com.zoeeasy.cloud.collect.core.handler;

import com.zoeeasy.cloud.collect.cst.CollectConst;
import com.zoeeasy.cloud.collect.enums.ResultCodeEnum;
import com.zoeeasy.cloud.collect.msgbody.request.HeartBeatBody;
import com.zoeeasy.cloud.collect.msgbody.result.BaseResultBody;
import com.zoeeasy.cloud.collect.packets.CollectPacket;
import lombok.extern.slf4j.Slf4j;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.utils.json.Json;

import java.nio.charset.Charset;

/**
 * @Date: 2019/1/13
 * @author: wh
 */
@Slf4j
public class HeartBeatHandler extends AbstractBizHandler<HeartBeatBody> {

    public HeartBeatHandler() {
    }

    @Override
    public Class<HeartBeatBody> bodyClass() {
        return HeartBeatBody.class;
    }

    @Override
    public Object handler(CollectPacket packet, HeartBeatBody bsBody, ChannelContext channelContext) throws Exception {
        String str = Json.toJson(bsBody);
        //log.info("[HeartBeatReqHandler.handler]收到心跳消息：--{}--", str);
        CollectPacket collectPacketBack = new CollectPacket();
        //心跳返回参数
        BaseResultBody baseResultBody = new BaseResultBody();
        baseResultBody.setService(bsBody.getService());
        baseResultBody.setMessage(CollectConst.HEARTBEAT_MESSAGE);
        baseResultBody.setCode(ResultCodeEnum.SUCCESS.getValue());
        String msg = Json.toJson(baseResultBody);
        collectPacketBack.setBody(msg.getBytes(Charset.forName(CollectConst.CHARSET)));
        Tio.send(channelContext, collectPacketBack);
        return null;
    }
}
