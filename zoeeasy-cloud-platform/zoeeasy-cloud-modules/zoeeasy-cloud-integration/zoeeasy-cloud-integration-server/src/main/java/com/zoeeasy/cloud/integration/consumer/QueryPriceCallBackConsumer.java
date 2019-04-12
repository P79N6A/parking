package com.zoeeasy.cloud.integration.consumer;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.scapegoat.boot.starter.rocketmq.annotation.RocketMQMessageListener;
import com.scapegoat.boot.starter.rocketmq.core.RocketMQListener;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.NumberUtils;
import com.scapegoat.infrastructure.lock.redisson.core.Lock;
import com.scapegoat.infrastructure.lock.redisson.core.LockFactory;
import com.scapegoat.infrastructure.lock.redisson.core.LockInfo;
import com.scapegoat.infrastructure.lock.redisson.enumerate.LockType;
import com.zoeeasy.cloud.core.cst.MessageQueueDefinitions;
import com.zoeeasy.cloud.integration.order.ParkingOrderIntegrationService;
import com.zoeeasy.cloud.message.MessageSendService;
import com.zoeeasy.cloud.message.payload.QueryPriceCallBackPayload;
import com.zoeeasy.cloud.order.enums.ThirdOrderSyncStatusEnum;
import com.zoeeasy.cloud.order.hikvision.ThirdParkingOrderService;
import com.zoeeasy.cloud.order.hikvision.dto.request.AnyParkingOrderSaveRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Inmier
 * @date 2019-03-21
 */
@Service
@RocketMQMessageListener(topic = MessageQueueDefinitions.Topic.THIRD_QUERY_PRICE_CALL_BACK, consumerGroup =
        MessageQueueDefinitions.ConsumeGroup.THIRD_QUERY_PRICE_CALL_BACK)
@Slf4j
public class QueryPriceCallBackConsumer implements RocketMQListener<QueryPriceCallBackPayload> {

    @Autowired
    private ParkingOrderIntegrationService parkingOrderIntegrationService;

    @Override
    public void onMessage(QueryPriceCallBackPayload payload) {

        if (payload != null) {
            log.debug("------[接收到要处理的查询价格结果处理消息{}]--------[开始处理时间{}]------",
                    JSON.toJSONString(payload), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
            try {
                parkingOrderIntegrationService.updateOrderStatus(payload);
            } catch (Exception e) {
                log.error("查询价格结果处理失败", e.getMessage());
            }
            log.debug("------[接收到要处理的查询价格结果处理消息{}]--------[结束处理时间{}]------", JSON.toJSONString(payload), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
        }
    }
}
