package com.zoeeasy.cloud.integration.mq.listener;

import com.scapegoat.boot.starter.rocketmq.annotation.RocketMQTransactionListener;
import com.scapegoat.boot.starter.rocketmq.core.RocketMQLocalTransactionListener;
import com.scapegoat.boot.starter.rocketmq.core.RocketMQLocalTransactionState;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.core.cst.MessageQueueDefinitions;
import com.zoeeasy.cloud.message.payload.PassingRecordToInspectPayload;
import com.zoeeasy.cloud.pms.enums.ParkingLotStatusEnum;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingLotResultDto;
import com.zoeeasy.cloud.pms.platform.PlatformParkingInfoService;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingLotInfoGetByCodeRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * 巡检入车消息推送-事务消息监听
 * 判断车位状态是否变更
 *
 * @author wangfeng
 * @since 2018/11/30 11:52
 **/
//@Component
@Slf4j
//@RocketMQTransactionListener(txProducerGroup = MessageQueueDefinitions.TxProducerGroup.INSPECT_ENTER_PUSH)
public class InspectEnterMQLocalTransactionListener implements RocketMQLocalTransactionListener {
    @Autowired
    private PlatformParkingInfoService platformParkingInfoService;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        if (checkParkingLotStatus(msg)) {
            return RocketMQLocalTransactionState.COMMIT_MESSAGE;
        }
        log.error("executeLocalTransaction {}", RocketMQLocalTransactionState.UNKNOW);
        return RocketMQLocalTransactionState.UNKNOW;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        if (checkParkingLotStatus(msg)) {
            return RocketMQLocalTransactionState.COMMIT_MESSAGE;
        }
        log.error("checkLocalTransaction {}", RocketMQLocalTransactionState.ROLLBACK_MESSAGE);
        return RocketMQLocalTransactionState.ROLLBACK_MESSAGE;
    }

    /**
     * 判断车位状态是否变更
     *
     * @param msg Message
     * @return boolean
     */
    private boolean checkParkingLotStatus(Message msg) {
        final Object payload = msg.getPayload();
        String parkingLotCode = null;
        if (payload instanceof PassingRecordToInspectPayload) {
            parkingLotCode = ((PassingRecordToInspectPayload) payload).getParkingLotCode();
        }
        if (StringUtils.isEmpty(parkingLotCode)) {
            return false;
        }
        final ParkingLotInfoGetByCodeRequestDto requestDto = new ParkingLotInfoGetByCodeRequestDto();
        requestDto.setParkingLotCode(parkingLotCode);
        final ObjectResultDto<ParkingLotResultDto> lot = platformParkingInfoService.getParkingLotByCode(requestDto);
        return lot.isSuccess() && lot.getData() != null &&
                ParkingLotStatusEnum.USED.getValue().equals(lot.getData().getStatus());
    }
}
