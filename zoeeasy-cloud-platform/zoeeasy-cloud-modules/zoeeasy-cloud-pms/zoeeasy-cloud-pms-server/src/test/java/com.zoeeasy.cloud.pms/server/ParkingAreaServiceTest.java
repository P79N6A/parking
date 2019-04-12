package com.zoeeasy.cloud.pms.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.parkingarea.ParkingAreaService;
import com.zoeeasy.cloud.pms.parkingarea.dto.request.*;
import com.zoeeasy.cloud.pms.parkingarea.dto.result.ParkingAreaListResultDto;
import com.zoeeasy.cloud.pms.parkingarea.dto.result.ParkingAreaQueryPagedResultDto;
import com.zoeeasy.cloud.pms.parkingarea.dto.result.ParkingAreaResultDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by song on 2018/10/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
@Transactional
public class ParkingAreaServiceTest {

    @Autowired
    private ParkingAreaService parkingAreaService;

    @Test
    public void testAddParkingArea() {
        ParkingAreaAddRequestDto requestDto = new ParkingAreaAddRequestDto();
        requestDto.setParkingId(32L);
        requestDto.setGarageId(1L);
        requestDto.setCode("852147");
        requestDto.setName("泊车区域");
        requestDto.setLotTotal(100);
        requestDto.setLotFixed(80);
        requestDto.setLotAvailable(50);
        requestDto.setRemark("备注备注备注");
        ResultDto resultDto = parkingAreaService.addParkingArea(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testUpdateParkingArea() {
        ParkingAreaUpdateRequestDto requestDto = new ParkingAreaUpdateRequestDto();
        requestDto.setId(1L);
        requestDto.setName("泊车区域");
        requestDto.setLotTotal(100);
        requestDto.setLotFixed(80);
        requestDto.setLotAvailable(50);
        requestDto.setRemark("备注备注备注");
        ResultDto resultDto = parkingAreaService.updateParkingArea(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testDeleteParkingArea() {
        ParkingAreaDeleteRequestDto requestDto = new ParkingAreaDeleteRequestDto();
        requestDto.setId(1L);
        ResultDto resultDto = parkingAreaService.deleteParkingArea(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testGetParkingAreaPagedList() {
        ParkingAreaQueryPagedRequestDto requestDto = new ParkingAreaQueryPagedRequestDto();
        requestDto.setAreaCode("440000");
        requestDto.setLotType("1");
        requestDto.setParkingName("2号停车场");
        requestDto.setCode("a002");
        requestDto.setName("002区");
        PagedResultDto<ParkingAreaQueryPagedResultDto> resultDto = parkingAreaService.getParkingAreaPagedList(requestDto);
        List<ParkingAreaQueryPagedResultDto> list = resultDto.getItems();
        assertTrue(resultDto.isSuccess());
        assertNotNull(list);
    }

    @Test
    public void testGetParkingArea() {
        ParkingAreaGetRequestDto requestDto = new ParkingAreaGetRequestDto();
        requestDto.setId(1L);
        ObjectResultDto<ParkingAreaResultDto> resultDto = parkingAreaService.getParkingArea(requestDto);
        ParkingAreaResultDto parkingAreaResultDto = resultDto.getData();
        assertTrue(resultDto.isSuccess());
        assertNotNull(parkingAreaResultDto);
    }

    @Test
    public void testGetParkingAreaList() {
        ParkingAreaListRequestDto requestDto = new ParkingAreaListRequestDto();
        requestDto.setParkingId(2L);
        requestDto.setGarageId(3L);
        requestDto.setName("002区");
        ListResultDto<ParkingAreaListResultDto> resultDto = parkingAreaService.getParkingAreaList(requestDto);
        List<ParkingAreaListResultDto> list = resultDto.getItems();
        assertTrue(resultDto.isSuccess());
        assertNotNull(list);
    }

}
