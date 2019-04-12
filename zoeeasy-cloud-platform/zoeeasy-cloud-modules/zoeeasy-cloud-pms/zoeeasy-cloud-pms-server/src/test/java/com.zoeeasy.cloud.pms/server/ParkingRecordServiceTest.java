package com.zoeeasy.cloud.pms.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zoeeasy.cloud.pms.park.ParkingRecordService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingRecordPagedResultRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingRecordViewResultDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * @author AkeemSuper
 * @date 2018/11/14 0014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
public class ParkingRecordServiceTest {
    @Autowired
    private ParkingRecordService parkingRecordService;

    /**
     * 分页获取停车记录
     */
    @Test
    public void getParkingRecordPageList() {
        ParkingRecordPagedResultRequestDto parkingRecordPagedResultRequestDto = new ParkingRecordPagedResultRequestDto();
        PagedResultDto<ParkingRecordViewResultDto> parkingRecordPageList =
                parkingRecordService.getParkingRecordPageList(parkingRecordPagedResultRequestDto);
        assertTrue(parkingRecordPageList.isSuccess());
    }
}
