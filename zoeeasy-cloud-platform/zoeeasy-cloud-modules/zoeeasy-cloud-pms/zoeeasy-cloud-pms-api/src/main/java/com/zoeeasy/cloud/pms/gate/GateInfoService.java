package com.zoeeasy.cloud.pms.gate;

import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.gate.dto.request.AddGateInfoRequestDto;
import com.zoeeasy.cloud.pms.gate.dto.request.UpdateGateInfoRequestDto;
import com.zoeeasy.cloud.pms.gate.dto.result.AddGateInfoResultDto;
import com.zoeeasy.cloud.pms.gate.dto.result.UpdateGateInfoResultDto;

/**
 * 云平台提供出入口服务
 *
 * @Date: 2018/11/30
 * @author: lhj
 */
public interface GateInfoService {

    /**
     * 添加出入口
     *
     * @param requestDto
     * @return
     */
    AddGateInfoResultDto addGateInfo(AddGateInfoRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 修改出入口
     *
     * @param requestDto
     * @return
     */
    UpdateGateInfoResultDto updateGateInfo(UpdateGateInfoRequestDto requestDto) throws ValidationException, BusinessException;
}
