package com.zoeeasy.cloud.integration.open;

import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.core.enums.PassingVehicleDataSourceEnum;
import com.zoeeasy.cloud.integration.open.dto.request.CloudAddParkingImageRequest;
import com.zoeeasy.cloud.integration.open.dto.request.CloudAddPassRecordRequestDto;
import com.zoeeasy.cloud.pms.garage.dto.result.CloudResultDto;

/**
 * @author AkeemSuper
 * @date 2018/12/1 0001
 */
public interface OpenPassRecordService {

    /**
     * 停车管理系统添加过车记录发送至消息队列
     * 自己保存图片到oss
     *
     * @param requestDto
     * @return
     */
    CloudResultDto clientAddPassRecord(CloudAddPassRecordRequestDto requestDto) throws ValidationException, BusinessException;


    /**
     * 停车管理系统添加过车记录发送至消息队列
     * 接受第三方的oss图片地址
     *
     * @param requestDto
     * @return
     */
    CloudResultDto clientSimpleAddPassRecord(CloudAddPassRecordRequestDto requestDto, String fullImage, String plateImage) throws ValidationException, BusinessException;


    /**
     * 停车管理系统添加过车记录发送至消息队列
     * 接受第三方停车的oss图片地址
     *
     * @param requestDto
     * @return
     */
    CloudResultDto addParkingImage(CloudAddParkingImageRequest requestDto);


    CloudResultDto clientSimpleAddPassRecord(CloudAddPassRecordRequestDto requestDto, String fullImage, String plateImage, String destination, PassingVehicleDataSourceEnum dataSource) throws ValidationException, BusinessException;



}
