package com.zoeeasy.cloud.pms.park;

import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.park.dto.request.AddParkingInfoRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.UpdateParkingInfoRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.AddParkingInfoResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.UpdateParkingInfoResultDto;

/**
 * 云平台提供给管理系统调用停车场接口
 */
public interface CloudParkingInfoService {

    /**
     * 停车场管理系统添加停车场
     *
     * @param requestDto
     * @return
     */
    AddParkingInfoResultDto addParkingInfo(AddParkingInfoRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 停车场管理系统修改停车场
     *
     * @param requestDto
     * @return
     */
    UpdateParkingInfoResultDto updateParkingInfo(UpdateParkingInfoRequestDto requestDto) throws ValidationException, BusinessException;

}
