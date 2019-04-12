package com.zoeeasy.cloud.integration.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.zoeeasy.cloud.gather.magnetic.dto.request.airjoy.AirJoyPushRequestDto;
import com.zoeeasy.cloud.integration.magnetic.AirJoyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author AkeemSuper
 * @date 2018/10/9 0009
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
public class AirJoyServiceTest {

    @Autowired
    private AirJoyService airJoyService;

    @Test
    public void testAirJoyHeartChangePush() {
        AirJoyPushRequestDto requestDto = new AirJoyPushRequestDto();
        requestDto.setDataType("2");
        requestDto.setMac("12587");
        requestDto.setParkingLotNo("1");
        requestDto.setStatus("1");
        requestDto.setUploadTime("20170721083010");
        airJoyService.airJoyHeartChangePush(requestDto);
    }

}