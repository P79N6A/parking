package com.zhuyitech.parking.integration.consumer;

import com.alibaba.fastjson.JSON;
import com.scapegoat.boot.starter.rocketmq.annotation.RocketMQMessageListener;
import com.scapegoat.boot.starter.rocketmq.core.RocketMQListener;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.zhuyitech.parking.integration.trade.PaymentTransactionIntegrationService;
import com.zoeeasy.cloud.core.cst.MessageQueueDefinitions;
import com.zoeeasy.cloud.message.payload.CustomerPaySuccessPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * 用户支付成功消息订阅
 *
 * @author walkman
 */
@Service
@RocketMQMessageListener(topic = MessageQueueDefinitions.Topic.CLOUD_TRADE_CUSTOMER_PAY_SUCCESS,
        consumerGroup = MessageQueueDefinitions.ConsumeGroup.CLOUD_TRADE_CUSTOMER_PAY_SUCCESS)
@Slf4j
public class PaySuccessMessageConsumer implements RocketMQListener<CustomerPaySuccessPayload> {

    @Autowired
    private PaymentTransactionIntegrationService paymentTransactionIntegrationService;

    @Override
    public void onMessage(CustomerPaySuccessPayload customerPaySuccessPayload) {

        try {

            if (customerPaySuccessPayload != null) {
                log.debug("------[接收到要处理的推送单用户停车消息{}]--------[开始处理时间{}]------",
                        JSON.toJSONString(customerPaySuccessPayload), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

                paymentTransactionIntegrationService.processPaySuccess(customerPaySuccessPayload);

                log.debug("------[接收到要处理的推送单用户停车消息{}]--------[结束处理时间{}]------", JSON.toJSONString(customerPaySuccessPayload),
                        DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
            }
            //手动消息应答
        } catch (Exception e) {
            log.error("推送单用户停车消息处理失败" + e.getMessage(), e);
        }
    }

}