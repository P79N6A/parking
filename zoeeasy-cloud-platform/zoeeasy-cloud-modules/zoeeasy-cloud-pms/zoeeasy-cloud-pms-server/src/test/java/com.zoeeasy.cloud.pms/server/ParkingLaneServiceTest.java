package com.zoeeasy.cloud.pms.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.lane.ParkingLaneInfoService;
import com.zoeeasy.cloud.pms.lane.dto.request.*;
import com.zoeeasy.cloud.pms.lane.dto.result.ParkingLaneListGetResultDto;
import com.zoeeasy.cloud.pms.lane.dto.result.ParkingLaneQueryPagedResultDto;
import com.zoeeasy.cloud.pms.lane.dto.result.ParkingLaneResultDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Kane on 2018/10/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
@Transactional
public class ParkingLaneServiceTest {
    @Autowired
    private ParkingLaneInfoService ParkingLaneInfoService;

    @Test
    public void testAddParkingLane() {
        ParkingLaneAddRequestDto requestDto = new ParkingLaneAddRequestDto();

        requestDto.setCode("999999");
        requestDto.setDirection(1);
        requestDto.setName("999");
        requestDto.setParkingId(39L);
        requestDto.setRemarks("sax");

        ResultDto resultDto = ParkingLaneInfoService.addParkingLane(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testUpdateParkingLane() {
        ParkingLaneUpdateRequestDto requestDto = new ParkingLaneUpdateRequestDto();
        requestDto.setId(1L);
        requestDto.setDirection(1);
        requestDto.setCode("1356");
        requestDto.setName("fast车道");
        requestDto.setParkingId(37L);
        requestDto.setRemarks("faster");
        ResultDto resultDto = ParkingLaneInfoService.updateParkingLane(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testDeleteParkingLane() {
        ParkingLaneDeleteRequestDto requestDto = new ParkingLaneDeleteRequestDto();
        requestDto.setId(1L);
        ResultDto resultDto = ParkingLaneInfoService.deleteParkingLane(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testGetParkingLane() {
        ParkingLaneGetRequestDto requestDto = new ParkingLaneGetRequestDto();
        requestDto.setId(1L);
        ObjectResultDto<ParkingLaneResultDto> resultDto = ParkingLaneInfoService.getParkingLane(requestDto);
        ParkingLaneResultDto ParkingLaneResultDto = resultDto.getData();
        assertTrue(resultDto.isSuccess());
        assertNotNull(ParkingLaneResultDto);
    }

    @Test
    public void testGetParkingLanePagedList() {
        ParkingLaneQueryPagedResultRequestDto requestDto = new ParkingLaneQueryPagedResultRequestDto();
        requestDto.setPageNo(1);
        requestDto.setPageSize(10);
        requestDto.setName("f");
        PagedResultDto<ParkingLaneQueryPagedResultDto> resultDto = ParkingLaneInfoService.getParkingLanePagedList(requestDto);
        List<ParkingLaneQueryPagedResultDto> list = resultDto.getItems();
        assertTrue(resultDto.isSuccess());
        assertNotNull(list);
    }

    @Test
    public void testGetGarageList() {
        ParkingLaneListGetRequestDto requestDto = new ParkingLaneListGetRequestDto();
        requestDto.setParkingId(37L);
        requestDto.setName("f");
        ListResultDto<ParkingLaneListGetResultDto> resultDto = ParkingLaneInfoService.getParkingLaneList(requestDto);
        List<ParkingLaneListGetResultDto> list = resultDto.getItems();
        assertTrue(resultDto.isSuccess());
        assertNotNull(list);
    }

}
