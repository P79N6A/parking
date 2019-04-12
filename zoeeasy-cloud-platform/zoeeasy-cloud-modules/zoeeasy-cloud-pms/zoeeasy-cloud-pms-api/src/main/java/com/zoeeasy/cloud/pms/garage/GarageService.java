package com.zoeeasy.cloud.pms.garage;

import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.garage.dto.request.AddGarageInfoRequestDto;
import com.zoeeasy.cloud.pms.garage.dto.request.UpdateGarageInfoRequestDto;
import com.zoeeasy.cloud.pms.garage.dto.result.AddGarageInfoResultDto;
import com.zoeeasy.cloud.pms.garage.dto.result.UpdateGarageInfoResultDto;

/**
 * 云平台提供车库服务
 *
 * @Date: 2018/11/30
 * @author: lhj
 */
public interface GarageService {

    /**
     * 添加车库
     *
     * @param requestDto
     * @return
     */
    AddGarageInfoResultDto addGarageInfo(AddGarageInfoRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 修改车库信息
     *
     * @param requestDto
     * @return
     */
    UpdateGarageInfoResultDto updateGarageInfo(UpdateGarageInfoRequestDto requestDto) throws ValidationException, BusinessException;
}
