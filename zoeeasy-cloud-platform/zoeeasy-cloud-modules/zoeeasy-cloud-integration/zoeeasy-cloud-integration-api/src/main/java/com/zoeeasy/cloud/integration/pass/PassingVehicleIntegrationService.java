package com.zoeeasy.cloud.integration.pass;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.zoeeasy.cloud.integration.pass.dto.result.PassingVehicleProcessResultDto;
import com.zoeeasy.cloud.message.payload.AnyPassRecordImagePayload;
import com.zoeeasy.cloud.message.payload.MagneticPassingPayload;
import com.zoeeasy.cloud.message.payload.PassingVehiclePayload;
import com.zoeeasy.cloud.pms.enums.ParkingImageTypeEnum;

/**
 * 过车数据处理集成服务
 *
 * @author AkeemSupers
 * @since 2018/9/26 0026
 */
public interface PassingVehicleIntegrationService {

    /**
     * 处理第三方平台过车数据消息
     *
     * @param payload passingVehiclePayload
     * @return ResultDto
     * @throws BusinessException exception
     */
    ObjectResultDto<PassingVehicleProcessResultDto> processPassingVehicleRecord(PassingVehiclePayload payload) throws BusinessException;

    /**
     * 处理地磁过车数据消息
     *
     * @param payload passingVehiclePayload
     * @return ResultDto
     * @throws BusinessException exception
     */
    ResultDto processMagneticVehicleRecord(MagneticPassingPayload payload) throws BusinessException;

    /**
     * 任意停车平台图片消息处理
     *
     * @param anyPassRecordImagePayload
     * @return
     * @throws BusinessException
     */
    ResultDto anyPassRecordImageProcess(AnyPassRecordImagePayload anyPassRecordImagePayload) throws BusinessException;

    /**
     * 针对停车图片的处理
     * @param anyPassRecordImagePayload
     * @return
     * @throws BusinessException
     */
    ResultDto anyParkingRecordImageProcess(AnyPassRecordImagePayload anyPassRecordImagePayload) throws BusinessException;

    /**
     * 任意停车平台停车图片通用消息处理
     *
     * @param anyPassRecordImagePayload 图片Payload
     * @param retryTopic                异常重试队列
     * @param parkingImageTypeEnum      图片类型枚举
     * @return
     * @throws BusinessException
     */

    ResultDto anyRecordImageProcess(AnyPassRecordImagePayload anyPassRecordImagePayload, String retryTopic, ParkingImageTypeEnum parkingImageTypeEnum)  throws BusinessException;

}
