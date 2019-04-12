package com.zoeeasy.cloud.collect.core.handler;


import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.collect.core.MessageSendCollectService;
import com.zoeeasy.cloud.collect.dto.request.PaymentNotifyCallBackRequestDto;
import com.zoeeasy.cloud.collect.msgbody.result.PaymentNotifyResultBody;
import com.zoeeasy.cloud.collect.packets.CollectPacket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.tio.core.ChannelContext;
import org.tio.utils.json.Json;

import javax.annotation.PostConstruct;

/**
 * @Date: 2019-03-01
 * @author: wf
 */
@Slf4j
public class PaymentNotifyHandler extends AbstractBizHandler<PaymentNotifyResultBody> {

    @Autowired
    private MessageSendCollectService messageSendCollectService;

    private PaymentNotifyHandler paymentNotifyHandler;

    @PostConstruct
    public void init() {
        paymentNotifyHandler = this;
        paymentNotifyHandler.messageSendCollectService = this.messageSendCollectService;
    }

    public PaymentNotifyHandler() {
    }

    @Override
    public Class<PaymentNotifyResultBody> bodyClass() {
        return PaymentNotifyResultBody.class;
    }

    @Override
    public Object handler(CollectPacket packet, PaymentNotifyResultBody bsBody, ChannelContext channelContext) throws Exception {
        String str = Json.toJson(bsBody);
        log.info("[PaymentNotifyHandler.handler]收到支付回传返回消息：--{}--", str);

        PaymentNotifyCallBackRequestDto requestDto = new PaymentNotifyCallBackRequestDto();
        requestDto.setLocalCode(bsBody.getLocalCode());
        requestDto.setParkingOrderNo(bsBody.getParkingOrderNo());
        ResultDto resultDto = paymentNotifyHandler.messageSendCollectService.sendPaymentNotifyCallBackMessage(requestDto);
        if (resultDto.isSuccess()) {
            log.info("[PaymentNotifyHandler.handler]发送消息到MQ成功" + requestDto.toString());
        }
        return null;
    }
}