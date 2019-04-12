package com.zoeeasy.cloud.pms.parkingarea;

import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.parkingarea.dto.request.AddParkingAreaRequestDto;
import com.zoeeasy.cloud.pms.parkingarea.dto.request.UpdateParkingAreaRequestDto;
import com.zoeeasy.cloud.pms.parkingarea.dto.result.AddParkingAreaResultDto;
import com.zoeeasy.cloud.pms.parkingarea.dto.result.UpdateParkingAreaResultDto;

/**
 * @Date: 2018/12/1
 * @author: lhj
 */
public interface CloudParkingAreaService {

    /**
     * 添加停车区域
     *
     * @param requestDto
     * @return
     */
    AddParkingAreaResultDto addParkingArea(AddParkingAreaRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 修改停车区域
     *
     * @param requestDto
     * @return
     */
    UpdateParkingAreaResultDto updateParkingArea(UpdateParkingAreaRequestDto requestDto) throws ValidationException, BusinessException;
}
