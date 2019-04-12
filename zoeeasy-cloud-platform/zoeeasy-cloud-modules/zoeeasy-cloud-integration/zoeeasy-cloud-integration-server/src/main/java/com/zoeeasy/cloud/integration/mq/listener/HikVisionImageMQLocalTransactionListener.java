package com.zoeeasy.cloud.integration.mq.listener;

import com.scapegoat.boot.starter.rocketmq.core.RocketMQLocalTransactionListener;
import com.scapegoat.boot.starter.rocketmq.core.RocketMQLocalTransactionState;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.message.payload.HikPassingImageFetchPayload;
import com.zoeeasy.cloud.pms.platform.PlatformProcessService;
import com.zoeeasy.cloud.pms.platform.dto.request.PassingVehicleRecordGetRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.PassingVehicleRecordResultDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;

/**
 * 海康过车图像消息 -事务消息监听
 * 判断过车记录是否保存
 *
 * @author wangfeng
 * @since 2018/12/1 10:30
 **/
//@Component
@Slf4j
//@RocketMQTransactionListener(txProducerGroup = MessageQueueDefinitions.TxProducerGroup.HIKVISION_PASSING_IMAGE)
public class HikVisionImageMQLocalTransactionListener implements RocketMQLocalTransactionListener {
    @Autowired
    private PlatformProcessService platformProcessService;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        if (checkPassingVehicleRecord(msg)) {
            return RocketMQLocalTransactionState.COMMIT_MESSAGE;
        }
        log.error("executeLocalTransaction {}", RocketMQLocalTransactionState.UNKNOW);
        return RocketMQLocalTransactionState.UNKNOW;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        if (checkPassingVehicleRecord(msg)) {
            return RocketMQLocalTransactionState.COMMIT_MESSAGE;
        }
        log.error("checkLocalTransaction {}", RocketMQLocalTransactionState.UNKNOW);
        return RocketMQLocalTransactionState.ROLLBACK_MESSAGE;
    }

    /**
     * 判断过车记录是否保存
     *
     * @param msg Message
     * @return boolean
     */
    private boolean checkPassingVehicleRecord(Message msg) {
        final Object payload = msg.getPayload();
        String passingNo = null;
        if (payload instanceof HikPassingImageFetchPayload) {
            passingNo = ((HikPassingImageFetchPayload) payload).getPassingNo();
        }
        if (StringUtils.isEmpty(passingNo)) {
            return false;
        }
        final PassingVehicleRecordGetRequestDto requestDto = new PassingVehicleRecordGetRequestDto();
        requestDto.setPassingNo(passingNo);
        requestDto.setParkingId(((HikPassingImageFetchPayload) payload).getParkingId());
        final ObjectResultDto<PassingVehicleRecordResultDto> resultDto =
                platformProcessService.getPassVehicleRecord(requestDto);
        return resultDto.isSuccess() && resultDto.getData() != null;
    }
}
