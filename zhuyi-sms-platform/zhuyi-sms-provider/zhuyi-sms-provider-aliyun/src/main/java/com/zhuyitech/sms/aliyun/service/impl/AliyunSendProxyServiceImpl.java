package com.zhuyitech.sms.aliyun.service.impl;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.sms.aliyun.config.AliyunSmsProperties;
import com.zhuyitech.sms.dto.request.SmsSendRequestDto;
import com.zhuyitech.sms.dto.request.SmsSendResultDto;
import com.zhuyitech.sms.enums.AliErrorCodeEnum;
import com.zhuyitech.sms.enums.SmsResultCodeEnum;
import com.zhuyitech.sms.service.api.SmsSendProxyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 短信发送服务类
 *
 * @author walkman
 */
@Service("aliyunSendProxyService")
@Slf4j
public class AliyunSendProxyServiceImpl implements SmsSendProxyService {

    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     */
    private static final String product = "Dysmsapi";

    /**
     * 产品域名,开发者无需替换
     */
    private static final String domain = "dysmsapi.aliyuncs.com";

    @Autowired
    private AliyunSmsProperties aliyunSmsProperties;

    /**
     * 短信发送
     *
     * @return
     */
    @Override
    public ObjectResultDto<SmsSendResultDto> sendMessage(SmsSendRequestDto requestDto) {

        ObjectResultDto<SmsSendResultDto> resultDto = new ObjectResultDto<SmsSendResultDto>();
        if (StringUtils.isEmpty(aliyunSmsProperties.getAccessKeyId())) {
            return resultDto.failed("APPID为空");
        }
        if (StringUtils.isEmpty(aliyunSmsProperties.getAccessKeySecret())) {
            return resultDto.failed("APPSecrete为空");
        }
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(requestDto.getPhoneNumber());
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(requestDto.getSignName());
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(requestDto.getTemplateCode());
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(requestDto.getParamString());
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        request.setSmsUpExtendCode(requestDto.getExtend());
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId(requestDto.getExtend());
        //三分钟
        request.setReadTimeout(180000);
        request.setConnectTimeout(180000);
        try {
            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                    aliyunSmsProperties.getAccessKeyId(), aliyunSmsProperties.getAccessKeySecret());
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);
            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals(AliErrorCodeEnum.OK.name())) {
                SmsSendResultDto smsSendResult = new SmsSendResultDto();
                smsSendResult.setRequestId(sendSmsResponse.getRequestId());
                smsSendResult.setBizId(sendSmsResponse.getBizId());
                resultDto.setData(smsSendResult);
                resultDto.success();
            } else {
                resultDto.failed(sendSmsResponse.getMessage());
            }
        } catch (ClientException e) {
            log.error("短信发送异常", e);
            resultDto.makeResult(SmsResultCodeEnum.SMS_SEND_FAIL.getCode(),
                    SmsResultCodeEnum.SMS_SEND_FAIL.getMessage());
        }
        return resultDto;
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

    public QuerySendDetailsResponse querySendDetails(String bizId) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliyunSmsProperties.getAccessKeyId(), aliyunSmsProperties.getAccessKeySecret());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        //必填-号码
        request.setPhoneNumber("15000000000");
        //可选-流水号
        request.setBizId(bizId);
        //必填-发送日期 支持30天内记录查询，格式yyyyMMdd
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        request.setSendDate(ft.format(new Date()));
        //必填-页大小
        request.setPageSize(10L);
        //必填-当前页码从1开始计数
        request.setCurrentPage(1L);
        //hint 此处可能会抛出异常，注意catch
        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);
        return querySendDetailsResponse;
    }
}
