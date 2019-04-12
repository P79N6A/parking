package com.zhuyitech.sms;


import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.sms.dto.request.TemplateAddRequestDto;
import com.zhuyitech.sms.dto.request.TemplateDeleteRequestDto;
import com.zhuyitech.sms.dto.request.TemplateGetRequestDto;
import com.zhuyitech.sms.dto.request.TemplateUpdateRequestDto;
import com.zhuyitech.sms.dto.result.SmsTemplateResultDto;
import com.zhuyitech.sms.service.api.SmsTemplateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
public class SmsTemplateServiceTest {

    @Autowired
    private SmsTemplateService templateService;

    @Test
    public void addTemplateTest() {
        TemplateAddRequestDto templateAddRequestDto = new TemplateAddRequestDto();
        templateAddRequestDto.setClientId("test");
        templateAddRequestDto.setTemplateId("5");
        templateAddRequestDto.setStatus(1);
        templateAddRequestDto.setContent("${code1},${code2},${code3}");
        templateService.addTemplate(templateAddRequestDto);
    }

    /**
     */
    @Test
    public void getTemplateTest() {
        TemplateGetRequestDto templateGetRequestDto = new TemplateGetRequestDto();
        templateGetRequestDto.setId(33l);
        ObjectResultDto<SmsTemplateResultDto> resultDto = templateService.getTemplate(templateGetRequestDto);
        assertTrue(resultDto.isSuccess());
        assertNotNull(resultDto.getData());
    }

    @Test
    public void deleteTemplateTest() {
        TemplateDeleteRequestDto templateDeleteRequestDto = new TemplateDeleteRequestDto();
        templateDeleteRequestDto.setId(32l);
        ResultDto resultDto = templateService.deleteTemplate(templateDeleteRequestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void updateTemplateTest() {
        TemplateUpdateRequestDto templateUpdateRequestDto = new TemplateUpdateRequestDto();
        templateUpdateRequestDto.setId(33l);
        templateUpdateRequestDto.setClientId("5");
        templateService.updateTemplate(templateUpdateRequestDto);
    }
}
