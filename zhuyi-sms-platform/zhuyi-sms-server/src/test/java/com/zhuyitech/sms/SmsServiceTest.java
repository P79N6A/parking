package com.zhuyitech.sms;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.sms.dto.request.MessageSendRequestDto;
import com.zhuyitech.sms.dto.request.VerifyCodeCheckRequestDto;
import com.zhuyitech.sms.dto.request.VerifyCodeCheckResultDto;
import com.zhuyitech.sms.service.api.SmsSendService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;


/**
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
public class SmsServiceTest {

    @Autowired
    private SmsSendService smsSendService;

    @Test
    public void sendMsgTest() {
        MessageSendRequestDto messageSendRequestDto = new MessageSendRequestDto();
        messageSendRequestDto.setClientId("testParking");
        messageSendRequestDto.setPhoneNumber("13588361944");
        messageSendRequestDto.setTemplateId("testTemplate");
        HashMap<String, String> sms = new HashMap<>();
        sms.put("code1", "您好！");
        sms.put("code2", "XX！");
        sms.put("verifyCode", "XX！");
        messageSendRequestDto.setParameters(sms);
        ResultDto resultDto = smsSendService.sendSms(messageSendRequestDto);

        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void sendVerifyMsgTest() {
        MessageSendRequestDto messageSendRequestDto = new MessageSendRequestDto();
        messageSendRequestDto.setClientId("testParking");
        messageSendRequestDto.setPhoneNumber("13588361944");
        messageSendRequestDto.setTemplateId("testTemplate");
        HashMap<String, String> sms = new HashMap<>();
        sms.put("code1", "您好！");
        sms.put("code2", "XX！");
        sms.put("code", "XX！");
        messageSendRequestDto.setParameters(sms);
        ResultDto resultDto = smsSendService.sendVerifySms(messageSendRequestDto);

        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void checkVerificationCodeEffectiveTest() {

        VerifyCodeCheckRequestDto requestDto = new VerifyCodeCheckRequestDto();
        requestDto.setClientId("testParking");
        requestDto.setPhoneNumber("13588361944");
        requestDto.setVerifyCode("226787");
        ObjectResultDto<VerifyCodeCheckResultDto> resultDto = smsSendService.checkVerifyCode(requestDto);
        assertTrue(resultDto.isSuccess());
    }
}
