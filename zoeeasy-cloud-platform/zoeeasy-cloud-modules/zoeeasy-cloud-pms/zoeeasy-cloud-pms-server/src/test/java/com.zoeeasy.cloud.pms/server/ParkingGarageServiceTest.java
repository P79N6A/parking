package com.zoeeasy.cloud.pms.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.garage.ParkingGarageService;
import com.zoeeasy.cloud.pms.garage.dto.request.*;
import com.zoeeasy.cloud.pms.garage.dto.result.GarageListGetResultDto;
import com.zoeeasy.cloud.pms.garage.dto.result.GarageQueryPagedResultDto;
import com.zoeeasy.cloud.pms.garage.dto.result.GarageResultDto;
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
public class ParkingGarageServiceTest {

    @Autowired
    private ParkingGarageService parkingGarageService;

    @Test
    public void testAddGarage() {
        GarageAddRequestDto requestDto = new GarageAddRequestDto();
        requestDto.setParkingId(2L);
        requestDto.setCode("814413148644");
        requestDto.setName("车库啊");
        requestDto.setLotCount(100);
        requestDto.setLotFixed(90);
        requestDto.setLotAvailable(50);
        requestDto.setRemark("备注备注");
        ResultDto resultDto = parkingGarageService.addGarage(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testUpdateGarage() {
        GarageUpdateRequestDto requestDto = new GarageUpdateRequestDto();
        requestDto.setId(1L);
        requestDto.setName("车库啊");
        requestDto.setLotCount(100);
        requestDto.setLotFixed(90);
        requestDto.setLotAvailable(50);
        requestDto.setRemark("备注备注");
        ResultDto resultDto = parkingGarageService.updateGarage(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testDeleteGarage() {
        GarageDeleteRequestDto requestDto = new GarageDeleteRequestDto();
        requestDto.setId(1L);
        ResultDto resultDto = parkingGarageService.deleteGarage(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testGetGarage() {
        GarageGetRequestDto requestDto = new GarageGetRequestDto();
        requestDto.setId(1L);
        ObjectResultDto<GarageResultDto> resultDto = parkingGarageService.getGarage(requestDto);
        GarageResultDto garageResultDto = resultDto.getData();
        assertTrue(resultDto.isSuccess());
        assertNotNull(garageResultDto);
    }

    @Test
    public void testGetGaragePagedList() {
        GarageQueryPagedRequestDto requestDto = new GarageQueryPagedRequestDto();
        requestDto.setPageNo(1);
        requestDto.setPageSize(10);
        requestDto.setAreaCode("440000");
        requestDto.setParkingName("2号停车场");
        requestDto.setName("002车库");
        requestDto.setCode("a002");
        PagedResultDto<GarageQueryPagedResultDto> resultDto = parkingGarageService.getGaragePagedList(requestDto);
        List<GarageQueryPagedResultDto> list = resultDto.getItems();
        assertTrue(resultDto.isSuccess());
        assertNotNull(list);
    }

    @Test
    public void testGetGarageList() {
        GarageListGetRequestDto requestDto = new GarageListGetRequestDto();
        requestDto.setParkingId(2L);
        requestDto.setName("002车库");
        ListResultDto<GarageListGetResultDto> resultDto = parkingGarageService.getGarageList(requestDto);
        List<GarageListGetResultDto> list = resultDto.getItems();
        assertTrue(resultDto.isSuccess());
        assertNotNull(list);
    }

}
