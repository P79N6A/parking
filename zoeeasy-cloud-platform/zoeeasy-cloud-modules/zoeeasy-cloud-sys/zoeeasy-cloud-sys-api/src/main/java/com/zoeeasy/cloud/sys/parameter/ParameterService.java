package com.zoeeasy.cloud.sys.parameter;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.sys.parameter.dto.request.ParamGetRequestDto;
import com.zoeeasy.cloud.sys.parameter.dto.request.ParamGetResultDto;
import com.zoeeasy.cloud.sys.parameter.dto.request.ParamItemGetByIdRequestDto;
import com.zoeeasy.cloud.sys.parameter.dto.request.ParamItemGetRequestDto;
import com.zoeeasy.cloud.sys.parameter.dto.request.ParamItemUpdateRequestDto;
import com.zoeeasy.cloud.sys.parameter.dto.request.ParamTypeRequestDto;
import com.zoeeasy.cloud.sys.parameter.dto.result.ParamItemResultDto;
import com.zoeeasy.cloud.sys.parameter.dto.result.ParamTypeResultDto;

/**
 * @author walkman
 */
public interface ParameterService {

    /**
     * 获取参数列表
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParamGetResultDto> paramGet(ParamGetRequestDto requestDto);

    /**
     * 修改参数
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto updateParam(ParamItemUpdateRequestDto requestDto);

    /**
     * 获取参数类型列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ParamTypeResultDto> getParamTypeList(ParamTypeRequestDto requestDto);

    /**
     * 根据参数类型获取参数数据列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ParamItemResultDto> getParamItemList(ParamItemGetRequestDto requestDto);

    /**
     * 根据参数项ID获取参数
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParamItemResultDto> getParamItemById(ParamItemGetByIdRequestDto requestDto);
}