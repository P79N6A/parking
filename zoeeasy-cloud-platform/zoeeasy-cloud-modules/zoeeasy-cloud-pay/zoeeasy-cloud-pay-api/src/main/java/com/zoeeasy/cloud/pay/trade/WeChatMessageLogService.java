package com.zoeeasy.cloud.pay.trade;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pay.trade.dto.request.message.WeChatMessageLogAddRequestDto;

/**
 * @author zwq
 */
public interface WeChatMessageLogService {

    /**
     * 新增通知日志
     *
     * @param requestDto
     * @return
     */
    ResultDto addMessageLog(WeChatMessageLogAddRequestDto requestDto);
}
