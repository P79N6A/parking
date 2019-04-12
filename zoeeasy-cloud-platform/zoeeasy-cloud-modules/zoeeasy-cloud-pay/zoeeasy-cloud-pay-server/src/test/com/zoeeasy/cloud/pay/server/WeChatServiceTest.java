package com.zoeeasy.cloud.ucc.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.zoeeasy.cloud.pay.wechat.params.WeChatUnifiedOrderParams;
import com.zoeeasy.cloud.pay.wechat.service.WeChatPayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.URLEncoder;

@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
public class WeChatServiceTest {

    @Autowired
    private WeChatPayService weChatPayService;

    @Test
    public void unifiedOrder() {

        WeChatUnifiedOrderParams requestDto = new WeChatUnifiedOrderParams();
        requestDto.setAppid("");
        requestDto.setBody("");
        requestDto.setMchId("");
        requestDto.setNonceStr("");
        requestDto.setNotifyURL("");
        requestDto.setOpenId("");
        requestDto.setOutTradeNo(URLEncoder.encode(""));
        requestDto.setTotalFee(1);
        requestDto.setSignKey("");
        requestDto.setSpbillCreateIp("");
        requestDto.setTradeType("APP");
        weChatPayService.unifiedOrder(requestDto);
    }

}
