package com.zhuyitech.parking.platform.controller.vcode;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.web.annotation.ApiVersion;
import com.zhuyitech.parking.platform.config.SmsConfig;
import com.zhuyitech.parking.ucc.dto.request.sms.VerifyCodeSendRequestDto;
import com.zhuyitech.parking.ucc.dto.request.sms.VerifyCodeValidateRequestDto;
import com.zhuyitech.sms.dto.request.MessageSendRequestDto;
import com.zhuyitech.sms.dto.request.VerifyCodeCheckRequestDto;
import com.zhuyitech.sms.dto.request.VerifyCodeCheckResultDto;
import com.zhuyitech.sms.enums.SmsResultCodeEnum;
import com.zhuyitech.sms.enums.VerifyTypeEnum;
import com.zhuyitech.sms.service.api.SmsSendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 短信控制器
 *
 * @author walkman
 */
@Api(tags = "短信", value = "短信Api", description = "短信Api", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/platform/vcode")
public class VerifyCodeController {

    @Autowired
    private SmsSendService smsSendService;

    @Autowired
    private SmsConfig smsConfig;

    /**
     * 发送短信验证码
     *
     * @param requestDto 发送短信验证码
     * @return ResultDto
     */
    @ApiOperation(value = "发送短信验证码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = ResultDto.class)
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ApiVersion(2)
    public ResultDto sendVerifyCode(VerifyCodeSendRequestDto requestDto) {

        ResultDto resultDto = new ResultDto();
        if (StringUtils.isEmpty(smsConfig.getClientId())) {
            return resultDto.makeResult(SmsResultCodeEnum.SMS_SEND_FAIL.getCode(),
                    SmsResultCodeEnum.SMS_SEND_FAIL.getMessage()
            );
        }
        MessageSendRequestDto messageSendRequestDto = new MessageSendRequestDto();
        if (requestDto.getVerifyType().equals(VerifyTypeEnum.REGISTER.getValue())) {
            messageSendRequestDto.setTemplateId(smsConfig.getRegisterTemplateId());
        } else if (requestDto.getVerifyType().equals(VerifyTypeEnum.LOGIN.getValue())) {
            messageSendRequestDto.setTemplateId(smsConfig.getLoginTemplateId());
        } else if (requestDto.getVerifyType().equals(VerifyTypeEnum.FORGETPASSWORD.getValue())) {
            messageSendRequestDto.setTemplateId(smsConfig.getForgetTemplateId());
        } else if (requestDto.getVerifyType().equals(VerifyTypeEnum.SETPASSWORD.getValue())) {
            messageSendRequestDto.setTemplateId(smsConfig.getSetTemplateId());
        } else if (requestDto.getVerifyType().equals(VerifyTypeEnum.PAYPASSWORD.getValue())) {
            messageSendRequestDto.setTemplateId(smsConfig.getPayTemplateId());
        } else {
            messageSendRequestDto.setTemplateId(smsConfig.getTemplateId());
        }
        if (StringUtils.isEmpty(messageSendRequestDto.getTemplateId())) {
            return resultDto.makeResult(SmsResultCodeEnum.SMS_SEND_FAIL.getCode(),
                    SmsResultCodeEnum.SMS_SEND_FAIL.getMessage()
            );
        }
        messageSendRequestDto.setClientId(smsConfig.getClientId());
        messageSendRequestDto.setPhoneNumber(requestDto.getPhoneNumber());
        messageSendRequestDto.setVerifyType(requestDto.getVerifyType());
        resultDto = smsSendService.sendVerifySms(messageSendRequestDto);
        return resultDto;
    }

    /**
     * 确认验证码是否有效
     *
     * @param requestDto requestDto
     * @return requestDto
     */
    @ApiOperation(value = "校验短信验证码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE, response = VerifyCodeCheckResultDto.class)
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ApiVersion(2)
    public ObjectResultDto<VerifyCodeCheckResultDto> checkVerifyCode(VerifyCodeValidateRequestDto requestDto) {
        ObjectResultDto<VerifyCodeCheckResultDto> resultDto = new ObjectResultDto<>();
        VerifyCodeCheckRequestDto verifyCodeCheckRequestDto = new VerifyCodeCheckRequestDto();
        if (StringUtils.isEmpty(smsConfig.getClientId())) {
            return resultDto.makeResult(SmsResultCodeEnum.VERIFY_CODE_CHECK_FAIL.getCode(),
                    SmsResultCodeEnum.VERIFY_CODE_CHECK_FAIL.getMessage()
            );
        }
        verifyCodeCheckRequestDto.setClientId(smsConfig.getClientId());
        verifyCodeCheckRequestDto.setVerifyCode(requestDto.getVerifyCode());
        verifyCodeCheckRequestDto.setPhoneNumber(requestDto.getPhoneNumber());
        if (StringUtils.isEmpty(requestDto.getSmsRequestId())) {
            verifyCodeCheckRequestDto.setSmsRequestId(requestDto.getSmsRequestId());
        }
        resultDto = smsSendService.checkVerifyCode(verifyCodeCheckRequestDto);
        return resultDto;
    }
}
