package com.zoeeasy.cloud.integration.inspect;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.integration.inspect.dto.request.InspectErrorRecordRequestDto;
import com.zoeeasy.cloud.integration.inspect.dto.request.InspectIntoRecordRequestDto;
import com.zoeeasy.cloud.integration.inspect.dto.request.InspectReasonListGetRequestDto;
import com.zoeeasy.cloud.integration.pass.dto.request.PassVehicleRecordAddRequestDto;

/**
 * 巡检过车记录服务
 *
 * @author AkeemSuper
 * @date 2018/10/18 0018
 */
public interface InspectRecordIntegrationService {

    /**
     * 入车巡检处理过车记录
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto inspectIntoPassRecord(InspectIntoRecordRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 误报巡检
     *
     * @param requestDto
     * @return
     */
    ResultDto inspectErrorRecord(InspectErrorRecordRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 人工添加过车记录
     *
     * @param requestDto
     * @return
     */
    ResultDto addPassRecord(PassVehicleRecordAddRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 获取巡检异常原因列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ComboboxItemDto> getInspectReason(InspectReasonListGetRequestDto requestDto);

}
