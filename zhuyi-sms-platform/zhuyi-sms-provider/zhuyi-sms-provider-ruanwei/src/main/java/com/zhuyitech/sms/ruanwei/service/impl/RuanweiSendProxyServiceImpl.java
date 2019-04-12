package com.zhuyitech.sms.ruanwei.service.impl;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.sms.dto.request.SmsSendRequestDto;
import com.zhuyitech.sms.dto.request.SmsSendResultDto;
import com.zhuyitech.sms.ruanwei.constant.Constants;
import com.zhuyitech.sms.ruanwei.utils.SmsClientSend;
import com.zhuyitech.sms.service.api.SmsSendProxyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 短信发送服务类
 *
 * @author walkman
 */
@Service("ruanweiSendProxyService")
public class RuanweiSendProxyServiceImpl implements SmsSendProxyService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static String SMS_URL = Constants.RUANWEI_SMS_URL;
    public static String SMS_USER_ID = Constants.RUANWEI_SMS_USER_ID;
    public static String SMS_ACCOUNT = Constants.RUANWEI_SMS_ACCOUNT;
    public static String SMS_PASSWORD = Constants.RUANWEI_SMS_PASSWORD;

    /**
     * 单条短信发送
     *
     * @param sendParameter
     * @return
     */
    @Override
    public ObjectResultDto<SmsSendResultDto> sendMessage(SmsSendRequestDto sendParameter) {
        ObjectResultDto<SmsSendResultDto> resultEx = new ObjectResultDto();
        try {

            String retMsg = SmsClientSend.sendSms(SMS_URL, SMS_USER_ID, SMS_ACCOUNT,
                    SMS_PASSWORD, sendParameter.getPhoneNumber(), sendParameter.getContent());
            if (retMsg.contains("Success")) {
                return resultEx.success();
            } else {
                return resultEx.failed(retMsg);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return resultEx.failed(e.getMessage());
        }
    }

    /**
     * 校验短信发送参数
     *
     * @param sendParameter
     * @return
     */
    @Override
    public ResultDto validateParameter(SmsSendRequestDto sendParameter) {
        ResultDto resultDto = new ResultDto();
        if (sendParameter == null) {
            return resultDto.failed("短信发送参数为空");
        }
        if (StringUtils.isEmpty(sendParameter.getPhoneNumber())) {
            return resultDto.failed("手机号码为空");
        }
        if (StringUtils.isEmpty(sendParameter.getContent())) {
            return resultDto.failed("短信内容为空");
        }
        return resultDto.success();
    }
}