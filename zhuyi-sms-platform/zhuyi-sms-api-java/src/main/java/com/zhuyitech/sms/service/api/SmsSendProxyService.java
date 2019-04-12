package com.zhuyitech.sms.service.api;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.sms.dto.request.SmsSendRequestDto;
import com.zhuyitech.sms.dto.request.SmsSendResultDto;

/**
 * 短信发送代理类(现有模拟实现、速盾实现)
 *
 * @author walkman
 */
public interface SmsSendProxyService {

    /**
     * 短信发送
     *
     * @return
     */
    ObjectResultDto<SmsSendResultDto> sendMessage(SmsSendRequestDto sendParameter);

    /**
     * 校验短信发送参数
     *
     * @param sendParameter
     * @return
     */
    ResultDto validateParameter(SmsSendRequestDto sendParameter);

}
