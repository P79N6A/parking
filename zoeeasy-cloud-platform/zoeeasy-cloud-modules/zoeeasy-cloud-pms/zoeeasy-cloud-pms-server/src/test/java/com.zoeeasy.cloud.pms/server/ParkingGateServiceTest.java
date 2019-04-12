package com.zoeeasy.cloud.pms.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.gate.ParkingGateInfoService;
import com.zoeeasy.cloud.pms.gate.dto.request.*;
import com.zoeeasy.cloud.pms.gate.dto.result.ParkingGateListGetResultDto;
import com.zoeeasy.cloud.pms.gate.dto.result.ParkingGateQueryPagedResultDto;
import com.zoeeasy.cloud.pms.gate.dto.result.ParkingGateResultDto;
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
public class ParkingGateServiceTest {
    @Autowired
    private ParkingGateInfoService parkingGateInfoService;

    @Test
    public void testAddParkingGate() {
        ParkingGateAddRequestDto requestDto = new ParkingGateAddRequestDto();

        requestDto.setCode("8888888");
        requestDto.setDirection(1);
        requestDto.setName("8出入口");
        requestDto.setParkingId(36L);
        requestDto.setRemarks("8888");

        ResultDto resultDto = parkingGateInfoService.addParkingGate(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testUpdateParkingGate() {
        ParkingGateUpdateRequestDto requestDto = new ParkingGateUpdateRequestDto();
        requestDto.setId(1055012704794554370L);
        requestDto.setDirection(1);
        requestDto.setCode("6666");
        requestDto.setName("6666");
        requestDto.setParkingId(37L);
        requestDto.setRemarks("string");
        ResultDto resultDto = parkingGateInfoService.updateParkingGate(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testDeleteParkingGate() {
        ParkingGateDeleteRequestDto requestDto = new ParkingGateDeleteRequestDto();
        requestDto.setId(1055015353463975938L);
        ResultDto resultDto = parkingGateInfoService.deleteParkingGate(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testGetParkingGate() {
        ParkingGateGetRequestDto requestDto = new ParkingGateGetRequestDto();
        requestDto.setId(1050197197213982721L);
        ObjectResultDto<ParkingGateResultDto> resultDto = parkingGateInfoService.getParkingGate(requestDto);
        ParkingGateResultDto parkingGateResultDto = resultDto.getData();
        assertTrue(resultDto.isSuccess());
        assertNotNull(parkingGateResultDto);
    }

    @Test
    public void testGetParkingGatePagedList() {
        ParkingGateQueryPagedResultRequestDto requestDto = new ParkingGateQueryPagedResultRequestDto();
        requestDto.setPageNo(1);
        requestDto.setPageSize(10);
        requestDto.setName("string");
        PagedResultDto<ParkingGateQueryPagedResultDto> resultDto = parkingGateInfoService.getParkingGatePagedList(requestDto);
        List<ParkingGateQueryPagedResultDto> list = resultDto.getItems();
        assertTrue(resultDto.isSuccess());
        assertNotNull(list);
    }

    @Test
    public void testGetGarageList() {
        ParkingGateListGetRequestDto requestDto = new ParkingGateListGetRequestDto();
        requestDto.setParkingId(36L);
        requestDto.setName("s");
        ListResultDto<ParkingGateListGetResultDto> resultDto = parkingGateInfoService.getParkingGateList(requestDto);
        List<ParkingGateListGetResultDto> list = resultDto.getItems();
        assertTrue(resultDto.isSuccess());
        assertNotNull(list);
    }
}
