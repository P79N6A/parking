package com.zoeeasy.cloud.collect.core.handler;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.collect.core.MessageSendCollectService;
import com.zoeeasy.cloud.collect.dto.request.QueryPriceCallBackRequestDto;
import com.zoeeasy.cloud.collect.msgbody.result.QueryPriceResultBody;
import com.zoeeasy.cloud.collect.packets.CollectPacket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.tio.core.ChannelContext;
import org.tio.utils.json.Json;

import javax.annotation.PostConstruct;

/**
 * @Date: 2019/1/12
 * @author: wh
 */
@Slf4j
public class QueryPriceHandler extends AbstractBizHandler<QueryPriceResultBody> {

    @Autowired
    private MessageSendCollectService messageSendCollectService;

    private QueryPriceHandler queryPriceHandler;

    @PostConstruct
    public void init() {
        queryPriceHandler = this;
        queryPriceHandler.messageSendCollectService = this.messageSendCollectService;
    }

    public QueryPriceHandler() {
    }

    @Override
    public Class<QueryPriceResultBody> bodyClass() {
        return QueryPriceResultBody.class;
    }

    @Override
    public Object handler(CollectPacket packet, QueryPriceResultBody bsBody, ChannelContext channelContext) {
        String str = Json.toJson(bsBody);
        log.info("[QueryPriceRespHandler.handler]收到订单价格查询返回消息：--{}--", str);

        QueryPriceCallBackRequestDto requestDto = new QueryPriceCallBackRequestDto();
        requestDto.setDuration(bsBody.getDuration());
        requestDto.setFreeOutTime(bsBody.getFreeOutTime());
        requestDto.setParkingOrderNo(bsBody.getParkingOrderNo());
        requestDto.setPassInTime(bsBody.getPassInTime());
        requestDto.setPlateNumber(bsBody.getPlateNumber());
        requestDto.setPrice(bsBody.getPrice());
        requestDto.setOrderNo(bsBody.getOrderNo());
        ResultDto resultDto = queryPriceHandler.messageSendCollectService.sendQueryPriceCallBackMessage(requestDto);
        if (resultDto.isSuccess()) {
            log.info("[QueryPriceRespHandler.handler]发送消息到MQ成功" + requestDto.toString());
        }
        return null;
    }
}
