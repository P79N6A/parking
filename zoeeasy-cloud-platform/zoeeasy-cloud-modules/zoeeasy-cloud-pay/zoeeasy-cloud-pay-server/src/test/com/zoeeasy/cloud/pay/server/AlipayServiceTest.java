package com.zoeeasy.cloud.pay.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.zoeeasy.cloud.pay.alipay.params.AlipayPrepareOrderParams;
import com.zoeeasy.cloud.pay.alipay.service.AliPayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.net.URLEncoder;

@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
public class AlipayServiceTest {

    @Autowired
    private AliPayService aliPayService;

    @Test
    public void preparePay() {

        AlipayPrepareOrderParams requestDto = new AlipayPrepareOrderParams();
        requestDto.setBody("");
        requestDto.setSubject("");
        requestDto.setOutTradeNo("");
        requestDto.setTimeoutExpress("");
        requestDto.setTotalAmount(new BigDecimal(0));
        requestDto.setGoodsType("");
        requestDto.setPassbackParams(URLEncoder.encode(""));
        requestDto.setEnablePayChannels("");
        requestDto.setDisablePayChannels("");
        aliPayService.preparePay(requestDto);
    }

    @Test
    public void testMenuGet() {

    }

    @Test
    public void testMenuDelete() {

    }

    @Test
    public void testMenuUpdate() {

    }
}
