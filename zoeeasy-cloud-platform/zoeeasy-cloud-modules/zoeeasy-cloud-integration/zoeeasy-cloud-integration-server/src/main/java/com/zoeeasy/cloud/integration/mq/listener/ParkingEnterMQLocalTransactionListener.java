package com.zoeeasy.cloud.integration.mq.listener;

import com.alibaba.fastjson.JSONObject;
import com.scapegoat.boot.starter.rocketmq.annotation.RocketMQTransactionListener;
import com.scapegoat.boot.starter.rocketmq.core.RocketMQLocalTransactionListener;
import com.scapegoat.boot.starter.rocketmq.core.RocketMQLocalTransactionState;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.core.cst.MessageQueueDefinitions;
import com.zoeeasy.cloud.message.payload.PushSingleParkingPayload;
import com.zoeeasy.cloud.order.parking.PlatformParkingOrderService;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderGetByOrderNoRequestDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderResultDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * 入场停车消息推送-事务消息监听
 * 判断停车账单是否生成
 *
 * @author wangfeng
 * @since 2018/11/30 11:50
 **/
//@Component
@Slf4j
//@RocketMQTransactionListener(txProducerGroup = MessageQueueDefinitions.TxProducerGroup.PARKING_ENTER_PUSH)
public class ParkingEnterMQLocalTransactionListener implements RocketMQLocalTransactionListener {

    @Autowired
    private PlatformParkingOrderService platformOrderService;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        if (checkOrderNo(msg)) {
            return RocketMQLocalTransactionState.COMMIT_MESSAGE;
        }
        log.error("executeLocalTransaction {}", RocketMQLocalTransactionState.UNKNOW);
        return RocketMQLocalTransactionState.UNKNOW;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        if (checkOrderNo(msg)) {
            return RocketMQLocalTransactionState.COMMIT_MESSAGE;
        }
        log.error("checkLocalTransaction {}", RocketMQLocalTransactionState.ROLLBACK_MESSAGE);
        return RocketMQLocalTransactionState.ROLLBACK_MESSAGE;
    }

    /**
     * 判断停车账单是否生成
     *
     * @param msg Message
     * @return boolean
     */
    private boolean checkOrderNo(Message msg) {
        final ParkingOrderGetByOrderNoRequestDto orderNoRequestDto = new ParkingOrderGetByOrderNoRequestDto();
        final Object payload = msg.getPayload();
        String orderNo = null;
        if (payload instanceof PushSingleParkingPayload) {
            JSONObject data = ((PushSingleParkingPayload) payload).getData();
            if (data != null) {
                orderNo = data.getString("orderNo");
            }
        }
        if (StringUtils.isEmpty(orderNo)) {
            return false;
        }
        //如果有一定不会为空
        orderNoRequestDto.setOrderNo(orderNo);
        ObjectResultDto<ParkingOrderResultDto> resultDto = platformOrderService.getByOrderNo(orderNoRequestDto);
        return resultDto.isSuccess() && resultDto.getData() != null;
    }
}
