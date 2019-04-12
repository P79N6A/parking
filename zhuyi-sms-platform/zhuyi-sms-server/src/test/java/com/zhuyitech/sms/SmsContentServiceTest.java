package com.zhuyitech.sms;


import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.zhuyitech.sms.dto.request.ContentQueryPagedResultRequestDto;
import com.zhuyitech.sms.service.api.SmsContentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
public class SmsContentServiceTest {

    @Autowired
    private SmsContentService smsContentService;

    @Test
    public void querySmsContentTest() {

        ContentQueryPagedResultRequestDto resultRequestDto = new ContentQueryPagedResultRequestDto();
        resultRequestDto.setPhoneNumber("15515733115");
        smsContentService.querySmsContent(resultRequestDto);

    }
}
