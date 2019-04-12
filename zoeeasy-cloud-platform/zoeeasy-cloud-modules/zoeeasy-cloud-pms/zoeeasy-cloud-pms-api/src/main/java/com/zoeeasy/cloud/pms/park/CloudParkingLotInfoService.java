package com.zoeeasy.cloud.pms.park;

import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.park.dto.request.AddParkingLotRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.UpdateParkingLotRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.AddParkingLotResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.UpdateParkingLotResultDto;

/**
 * 云平台提供给管理系统调用泊位接口
 */
public interface CloudParkingLotInfoService {

    /**
     * 停车场客户端添加泊位
     *
     * @param requestDto
     * @return
     */
    AddParkingLotResultDto addParkingLot(AddParkingLotRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 停车场客户端修改泊位
     *
     * @param requestDto
     * @return
     */
    UpdateParkingLotResultDto updateParkingLot(UpdateParkingLotRequestDto requestDto) throws ValidationException, BusinessException;

}
