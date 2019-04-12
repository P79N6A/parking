package com.zhuyitech.parking.integration.service.api;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.ucc.dto.request.realname.UserAuthApplyAddRequestDto;

/**
 * @author AkeemSuper
 * @date 2018/7/11 0011
 */
public interface RealNameIntegrationService {

    /**
     * 实名认证申请集成服务
     *
     * @param requestDto UserAuthApplyAddRequestDto
     * @return ResultDto
     */
    ResultDto addAuthApply(UserAuthApplyAddRequestDto requestDto);
}
