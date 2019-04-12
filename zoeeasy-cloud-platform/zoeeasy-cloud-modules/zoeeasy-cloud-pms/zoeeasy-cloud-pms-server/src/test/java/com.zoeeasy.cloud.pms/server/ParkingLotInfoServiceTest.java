package com.zoeeasy.cloud.pms.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.park.ParkingLotInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.*;
import com.zoeeasy.cloud.pms.park.dto.result.MagneticDetectorByParkingLotQueryPageResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingLotPagedResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingLotResultDto;
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
public class ParkingLotInfoServiceTest {

    @Autowired
    private ParkingLotInfoService parkingLotInfoService;

    @Test
    public void testAddParkingLot() {
        ParkingLotAddRequestDto requestDto = new ParkingLotAddRequestDto();
        requestDto.setParkingId(2L);
        requestDto.setGarageId(3L);
        requestDto.setParkingAreaId(3L);
        requestDto.setNumber("45873695");
        requestDto.setCode("123466898");
        requestDto.setDescription("天字第一号是谁");
        ResultDto resultDto = parkingLotInfoService.addParkingLot(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testDeleteParkingLot() {
        ParkingLotDeleteRequestDto requestDto = new ParkingLotDeleteRequestDto();
        requestDto.setId(1L);
        ResultDto resultDto = parkingLotInfoService.deleteParkingLot(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testUpdateParkingLot() {
        ParkingLotUpdateRequestDto requestDto = new ParkingLotUpdateRequestDto();
        requestDto.setId(1L);
//        requestDto.setParkingId(2L);
//        requestDto.setGarageId(3L);
//        requestDto.setParkingAreaId(3L);
//        requestDto.setNumber("45873695");
        requestDto.setNumber("15384");
        requestDto.setDescription("天字第一号是谁");
        ResultDto resultDto = parkingLotInfoService.updateParkingLot(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testGetParkingLot() {
        ParkingLotGetRequestDto requestDto = new ParkingLotGetRequestDto();
        requestDto.setId(1L);
        ObjectResultDto<ParkingLotResultDto> resultDto = parkingLotInfoService.getParkingLot(requestDto);
        ParkingLotResultDto parkingLotResultDto = resultDto.getData();
        assertTrue(resultDto.isSuccess());
        assertNotNull(parkingLotResultDto);
    }

    @Test
    public void testGetParkingLotPagedList() {
        ParkingLotQueryPagedResultRequestDto requestDto = new ParkingLotQueryPagedResultRequestDto();
        requestDto.setAreaCode("440000");
        requestDto.setParkingId(1L);
        requestDto.setCode("10001");
        requestDto.setNumber("mala");
        PagedResultDto<ParkingLotPagedResultDto> resultDto = parkingLotInfoService.getParkingLotPagedList(requestDto);
        List<ParkingLotPagedResultDto> list = resultDto.getItems();
        assertTrue(resultDto.isSuccess());
        assertNotNull(list);
    }

    @Test
    public void testGetParkingLotList() {
        ParkingLotListGetRequestDto requestDto = new ParkingLotListGetRequestDto();
        requestDto.setParkingId(2L);
        requestDto.setNumber("mala");
        requestDto.setName("chaogemala");
        ListResultDto<ParkingLotResultDto> resultDto = parkingLotInfoService.getParkingLotList(requestDto);
        List<ParkingLotResultDto> list = resultDto.getItems();
        assertTrue(resultDto.isSuccess());
        assertNotNull(list);
    }

    @Test
    public void testgetMagneticDetectorByParkingLotPagedList() {
        MagneticDetectorByParkingLotQueryPageRequestDto requestDto = new MagneticDetectorByParkingLotQueryPageRequestDto();
        requestDto.setAreaCode("440000");
        requestDto.setNumber("100001");
        requestDto.setCode("100001");
        requestDto.setParkingId(36L);
        PagedResultDto<MagneticDetectorByParkingLotQueryPageResultDto> magneticDetectorByParkingLotPagedList = parkingLotInfoService.getMagneticDetectorByParkingLotPagedList(requestDto);
        List<MagneticDetectorByParkingLotQueryPageResultDto> dtos = magneticDetectorByParkingLotPagedList.getItems();
        assertTrue(magneticDetectorByParkingLotPagedList.isSuccess());
        assertNotNull(dtos);
    }

}
