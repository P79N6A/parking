package com.zoeeasy.cloud.pay.parameter;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.pay.parameter.dto.request.ParamConfigGetRequestDto;
import com.zoeeasy.cloud.pay.parameter.dto.result.ParamConfigGetResultDto;

/**
 * 获取微信支付宝参数服务
 *
 * @author AkeemSuper
 * @date 2018/12/25 0025
 */
public interface ParameterConfigService {

    /**
     * 获取参数列表
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParamConfigGetResultDto> paramConfigGet(ParamConfigGetRequestDto requestDto);
}
