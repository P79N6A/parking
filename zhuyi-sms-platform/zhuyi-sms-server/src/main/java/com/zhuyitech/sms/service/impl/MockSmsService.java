package com.zhuyitech.sms.service.impl;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.sms.dto.request.SmsSendRequestDto;
import com.zhuyitech.sms.dto.request.SmsSendResultDto;
import com.zhuyitech.sms.service.api.SmsSendProxyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 短信发送服务类
 */
@Service("mockSmsService")
public class MockSmsService implements SmsSendProxyService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 短信发送
     *
     * @return
     */
    @Override
    public ObjectResultDto<SmsSendResultDto> sendMessage(SmsSendRequestDto sendParameter) {
        ObjectResultDto<SmsSendResultDto> objectResultDto = new ObjectResultDto<>();
        return objectResultDto;
    }

    /**
     * 校验短信发送参数
     *
     * @param sendParameter
     * @return
     */
    @Override
    public ResultDto validateParameter(SmsSendRequestDto sendParameter) {
        ResultDto resultEx = new ResultDto();
        return resultEx.success();
    }
}