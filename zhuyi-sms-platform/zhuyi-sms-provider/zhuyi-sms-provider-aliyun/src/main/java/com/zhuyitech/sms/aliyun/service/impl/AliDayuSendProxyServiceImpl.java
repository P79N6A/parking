package com.zhuyitech.sms.aliyun.service.impl;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.zhuyitech.sms.aliyun.config.AliyunSmsProperties;
import com.zhuyitech.sms.dto.request.SmsSendRequestDto;
import com.zhuyitech.sms.dto.request.SmsSendResultDto;
import com.zhuyitech.sms.service.api.SmsSendProxyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 短信发送服务类
 *
 * @author walkman
 */
@Service("alidayuSendProxyService")
@Slf4j
public class AliDayuSendProxyServiceImpl implements SmsSendProxyService {

    private static String SMS_TYPE = "normal";

    @Autowired
    private AliyunSmsProperties aliyunSmsProperties;

    /**
     * 单条短信发送
     *
     * @param sendParameter
     * @return
     */
    @Override
    public ObjectResultDto<SmsSendResultDto> sendMessage(SmsSendRequestDto sendParameter) {

        ObjectResultDto<SmsSendResultDto> resultDto = new ObjectResultDto<>();
        try {

            if (StringUtils.isEmpty(aliyunSmsProperties.getUrl())) {
                return resultDto.failed("URL为空");
            }
            if (StringUtils.isEmpty(aliyunSmsProperties.getAccessKeyId())) {
                return resultDto.failed("APPID为空");
            }
            if (StringUtils.isEmpty(aliyunSmsProperties.getAccessKeySecret())) {
                return resultDto.failed("APPSecrete为空");
            }

            TaobaoClient client = new DefaultTaobaoClient(aliyunSmsProperties.getUrl(), aliyunSmsProperties.getAccessKeyId(), aliyunSmsProperties.getAccessKeySecret());
            AlibabaAliqinFcSmsNumSendRequest sendRequest = new AlibabaAliqinFcSmsNumSendRequest();
            if (!StringUtils.isEmpty(sendParameter.getExtend())) {
                sendRequest.setExtend(sendParameter.getExtend());
            }
            sendRequest.setSmsType(SMS_TYPE);
            sendRequest.setSmsFreeSignName(sendParameter.getSignName());
            sendRequest.setSmsParamString(sendParameter.getParamString());
            sendRequest.setSmsTemplateCode(sendParameter.getTemplateCode());
            AlibabaAliqinFcSmsNumSendResponse sendResponse = client.execute(sendRequest);
            if (sendResponse.isSuccess()) {
                resultDto.success();
            } else {
                resultDto.failed(sendResponse.getMsg());
            }
            return resultDto;
        } catch (Exception e) {
            log.error(e.getMessage());
            return resultDto.failed(e.getMessage());
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
        if (StringUtils.isEmpty(sendParameter.getSignName())) {
            return resultDto.failed("短信签名内容为空");
        }
        if (StringUtils.isEmpty(sendParameter.getTemplateCode())) {
            return resultDto.failed("短信模板ID");
        }
        return resultDto.success();
    }
}