package com.zhuyitech.sms.service.api;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.sms.dto.request.VerifyCodeCheckRequestDto;
import com.zhuyitech.sms.dto.request.MessageSendRequestDto;
import com.zhuyitech.sms.dto.request.VerifyCodeCheckResultDto;

/**
 * 短信发送
 *
 * @author walkman
 */
public interface SmsSendService {

    /**
     * 单纯的格式模板短信发送
     *
     * @param requestDto
     * @return
     */
    ResultDto sendSms(MessageSendRequestDto requestDto);

    /**
     * 带校验的短信发送
     *
     * @param requestDto 发送参数
     * @return
     */
    ResultDto sendVerifySms(MessageSendRequestDto requestDto);

    /**
     * 确认验证码是否有效
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<VerifyCodeCheckResultDto> checkVerifyCode(VerifyCodeCheckRequestDto requestDto);

}
