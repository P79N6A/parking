package com.zhuyitech.sms;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.zhuyitech.sms.dto.request.VerifyCodeQueryPagedResultRequestDto;
import com.zhuyitech.sms.service.api.SmsVerifyCodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
public class SmsVerifyCodeServiceTest {

    @Autowired
    private SmsVerifyCodeService verifyCodeService;

    @Test
    public void queryVerifyCode() {
        VerifyCodeQueryPagedResultRequestDto verifyCodeParameter = new VerifyCodeQueryPagedResultRequestDto();
        verifyCodeParameter.setPhoneNumber("15515733115");
        verifyCodeService.queryVerifyCode(verifyCodeParameter);
    }
}
