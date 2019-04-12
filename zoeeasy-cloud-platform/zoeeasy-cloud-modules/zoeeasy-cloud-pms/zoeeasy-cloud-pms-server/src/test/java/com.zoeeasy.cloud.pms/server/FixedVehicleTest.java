package com.zoeeasy.cloud.pms.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.FixedVehicleService;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.FixedVehicleQueryPagedResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.FixedVehicleResultDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @date: 2018/10/20.
 * @author：zm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
@Transactional
public class FixedVehicleTest {

    @Autowired
    private FixedVehicleService fixedVehicleService;

    @Test
    public void testAddFixedVehicle() {
        FixedVehicleAddRequestDto requestDto = new FixedVehicleAddRequestDto();
        requestDto.setCarColor(1);
        requestDto.setCarType(1);
        requestDto.setFixedType(1);
        requestDto.setPlateColor(1);
        requestDto.setPlateNumber("浙A4532");
        requestDto.setParkingId(2L);
        requestDto.setOwnerName("逐一科技");
        requestDto.setOwnerPhone("15924182806");
//        requestDto.setParkingLotId(2L);
//        requestDto.setParkingLotNumber("125");
        requestDto.setBeginTime(new Date());
        requestDto.setEndTime(new Date());
        requestDto.setPlateType("其他");
        ResultDto resultDto = fixedVehicleService.addFixedVehicle(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testUpdateFixedVehicle() {
        FixedVehicleUpdateRequestDto requestDto = new FixedVehicleUpdateRequestDto();
        requestDto.setCarColor(2);
        requestDto.setCarType(2);
        requestDto.setEndTime(new Date());
        requestDto.setFixedType(2);
        requestDto.setOwnerName("逐一");
        requestDto.setOwnerPhone("15924182806");
        requestDto.setPlateColor(2);
        requestDto.setPlateType("教练车");
        ResultDto resultDto = fixedVehicleService.updateFixedVehicle(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testDeleteFixedVehicle() {
        FixedVehicleDeleteRequestDto requestDto = new FixedVehicleDeleteRequestDto();
        requestDto.setId(2L);
        ResultDto resultDto = fixedVehicleService.deleteFixedVehicle(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testGetFixedVehicle() {
        FixedVehicleGetRequestDto requestDto = new FixedVehicleGetRequestDto();
        requestDto.setId(2L);
        ObjectResultDto<FixedVehicleResultDto> resultDto = fixedVehicleService.getFixedVehicle(requestDto);
        FixedVehicleResultDto data = resultDto.getData();
        assertTrue(resultDto.isSuccess());
        assertNotNull(data);
    }

    @Test
    public void testRelevanceParkingLotId() {
        RelevanceParkingLotIdRequestDto requestDto = new RelevanceParkingLotIdRequestDto();
        requestDto.setId(2L);
        requestDto.setParkingLotId(1L);
        requestDto.setParkingLotNumber("0001");
        ResultDto resultDto = fixedVehicleService.relevanceParkingLotId(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testGetFixedVehiclePagedList() {
        FixedVehicleQueryPagedRequestDto requestDto = new FixedVehicleQueryPagedRequestDto();
        requestDto.setAreaCode("22");
        requestDto.setFixedType(1);
        requestDto.setParkingName("停车场");
        requestDto.setPlateNumber("浙A4561");
        requestDto.setPageNo(1);
        requestDto.setPageSize(10);
        PagedResultDto<FixedVehicleQueryPagedResultDto> resultDto = fixedVehicleService.getFixedVehiclePagedList(requestDto);
        List<FixedVehicleQueryPagedResultDto> items = resultDto.getItems();
        assertTrue(resultDto.isSuccess());
        assertNotNull(items);

    }
}
