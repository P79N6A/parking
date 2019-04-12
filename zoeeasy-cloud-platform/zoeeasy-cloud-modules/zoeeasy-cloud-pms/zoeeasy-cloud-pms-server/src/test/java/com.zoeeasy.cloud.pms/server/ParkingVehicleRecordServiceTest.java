package com.zoeeasy.cloud.pms.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zoeeasy.cloud.pms.park.ParkingVehicleRecordService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingVehicleRecordQueryPageRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingVehicleRecordViewResultDto;
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
public class ParkingVehicleRecordServiceTest {
    @Autowired
    private ParkingVehicleRecordService parkingVehicleRecordService;

    /**
     * 分页获取在停车辆
     */
    @Test
    public void getParkingVehicleRecordListPage() {
        ParkingVehicleRecordQueryPageRequestDto parkingVehicleRecordQueryPageRequestDto = new ParkingVehicleRecordQueryPageRequestDto();
        PagedResultDto<ParkingVehicleRecordViewResultDto> parkingVehicleRecordListPage =
                parkingVehicleRecordService.getParkingVehicleRecordListPage(parkingVehicleRecordQueryPageRequestDto);
        assertTrue(parkingVehicleRecordListPage.isSuccess());
    }
}
