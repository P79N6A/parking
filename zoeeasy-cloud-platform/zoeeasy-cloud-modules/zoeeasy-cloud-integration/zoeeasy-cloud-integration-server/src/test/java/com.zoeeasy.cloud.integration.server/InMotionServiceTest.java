package com.zoeeasy.cloud.integration.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.zoeeasy.cloud.gather.magnetic.dto.request.inmotion.InMotionHeartBeatPushRequestDto;
import com.zoeeasy.cloud.integration.magnetic.InMotionService;
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
public class InMotionServiceTest {

    @Autowired
    private InMotionService inMotionService;

    @Test
    public void testinMotionHeartBeat() {
        InMotionHeartBeatPushRequestDto requestDto = new InMotionHeartBeatPushRequestDto();
        requestDto.setCmd("12");
        requestDto.getBody().setParkID(36);
        requestDto.getBody().setBattary(30);
        requestDto.getBody().setDeviceID("1");
        requestDto.getBody().setTime("20170721083010");
        requestDto.getBody().setParkingStatu(1);
        requestDto.getBody().setErrcode(1);
        requestDto.getBody().setToken("1");
        inMotionService.inMotionHeartBeat(requestDto);
    }

}