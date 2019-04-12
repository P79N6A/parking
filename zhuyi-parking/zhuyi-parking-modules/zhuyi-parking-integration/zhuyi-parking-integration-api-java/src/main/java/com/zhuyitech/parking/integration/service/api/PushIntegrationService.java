package com.zhuyitech.parking.integration.service.api;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.message.payload.PushSingleParkingPayload;

/**
 * 推送集成服务
 *
 * @author walkman
 */
public interface PushIntegrationService {

    /**
     * 处理停车推送消息
     *
     * @param pushSingleParkingPayload pushSingleParkingPayload
     * @return
     */
    ResultDto processParkingPayload(PushSingleParkingPayload pushSingleParkingPayload);

}
