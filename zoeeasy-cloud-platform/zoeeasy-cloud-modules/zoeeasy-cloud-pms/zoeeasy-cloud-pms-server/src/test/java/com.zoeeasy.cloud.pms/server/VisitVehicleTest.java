package com.zoeeasy.cloud.pms.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.VisitVehicleService;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.VisitVehicleQueryPagedResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.VisitVehicleResultDto;
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
public class VisitVehicleTest {

    @Autowired
    private VisitVehicleService visitVehicleService;

    @Test
    public void testAddVisitVehicle() {
        VisitVehicleAddRequestDto requestDto = new VisitVehicleAddRequestDto();
        requestDto.setPlateType("其他");
        requestDto.setCarColor(3);
        requestDto.setCarType(2);
        requestDto.setOwnerName("逐一科技");
        requestDto.setOwnerPhone("15924182806");
        requestDto.setParkingId(2L);
        requestDto.setPlateColor(2);
        requestDto.setPlateNumber("浙A7535");
        requestDto.setVisitType(1);
        requestDto.setBeginTime(new Date());
        requestDto.setEndTime(new Date());
        ResultDto resultDto = visitVehicleService.addVisitVehicle(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testUpdateVisitVehicle() {
        VisitVehicleUpdateRequestDto requestDto = new VisitVehicleUpdateRequestDto();
        requestDto.setCarColor(1);
        requestDto.setCarType(1);
        requestDto.setOwnerName("逐一");
        requestDto.setOwnerPhone("15939133261");
        requestDto.setPlateColor(1);
        requestDto.setPlateType("教练车");
        requestDto.setVisitType(1);
        requestDto.setEndTime(new Date());
        ResultDto resultDto = visitVehicleService.updateVisitVehicle(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testDeleteVisitVehicle() {
        VisitVehicleDeleteRequestDto requestDto = new VisitVehicleDeleteRequestDto();
        requestDto.setId(2L);
        ResultDto resultDto = visitVehicleService.deleteVisitVehicle(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testGetVisitVehicle() {
        VisitVehicleGetRequestDto requestDto = new VisitVehicleGetRequestDto();
        requestDto.setId(2L);
        ObjectResultDto<VisitVehicleResultDto> resultDto = visitVehicleService.getVisitVehicle(requestDto);
        VisitVehicleResultDto data = resultDto.getData();
        assertTrue(resultDto.isSuccess());
        assertNotNull(data);
    }

    @Test
    public void testGetVisitVehiclePagedList() {
        VisitVehicleQueryPagedRequestDto requestDto = new VisitVehicleQueryPagedRequestDto();
        requestDto.setAreaCode("22");
        requestDto.setParkingName("停车场");
        requestDto.setPlateNumber("浙A4768");
        requestDto.setPageNo(1);
        requestDto.setPageSize(10);
        PagedResultDto<VisitVehicleQueryPagedResultDto> resultDto = visitVehicleService.getVisitVehiclePagedList(requestDto);
        List<VisitVehicleQueryPagedResultDto> items = resultDto.getItems();
        assertTrue(resultDto.isSuccess());
        assertNotNull(items);
    }
}
