package com.zoeeasy.cloud.pay.trade;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pay.trade.dto.request.message.AlipayMessageLogAddRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.message.AlipayMessageLogGetRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.result.message.AlipayMessageLogResultDto;

/**
 * @author walkman
 */
public interface AlipayMessageLogService {

    /**
     * 新增通知日志
     *
     * @param requestDto
     * @return
     */
    ResultDto addMessageLog(AlipayMessageLogAddRequestDto requestDto);

    /**
     * 获取通知日志
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<AlipayMessageLogResultDto> getMessageLog(AlipayMessageLogGetRequestDto requestDto);
}
