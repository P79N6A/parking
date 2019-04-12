package com.zoeeasy.cloud.pms.dock;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.dock.dto.request.DockInfoGetByIdRequestDto;
import com.zoeeasy.cloud.pms.dock.dto.request.RegisterParkingRequestDto;
import com.zoeeasy.cloud.pms.dock.dto.result.DockInfoResultDto;
import com.zoeeasy.cloud.pms.garage.dto.result.CloudResultDto;

/**
 * 场库系统对接服务
 *
 * @author walkman
 */
public interface DockInfoService {

    /**
     * 客户端系统注册
     *
     * @param requestDto
     * @return
     * @throws ValidationException
     * @throws BusinessException
     */
    CloudResultDto registerParkingSystem(RegisterParkingRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 无租户
     *
     * @return
     */
    ObjectResultDto<DockInfoResultDto> getDockById(DockInfoGetByIdRequestDto requestDto);

}
