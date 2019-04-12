package com.zoeeasy.cloud.gather.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zoeeasy.cloud.gather.magnetic.MagneticHeartBeatService;
import com.zoeeasy.cloud.gather.magnetic.dto.request.magneticdetector.*;
import com.zoeeasy.cloud.gather.magnetic.dto.result.GetLastMagneticHeartBeatAddResultDto;
import com.zoeeasy.cloud.gather.magnetic.dto.result.MagneticReportRecordQueryPageResultDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
@Transactional(rollbackFor = Exception.class)
public class MagneticHeartBeatServiceTest {

    @Autowired
    private MagneticHeartBeatService magneticHeartBeatService;

    @Test
    public void testAddMagneticHeartBeat() {
        MagneticHeartBeatAddRequestDto requestDto = new MagneticHeartBeatAddRequestDto();
        requestDto.setTenantId(1L);
        requestDto.setBattery(3);
        requestDto.setBeatTime(new Date());
        requestDto.setDetectorId(1L);
        requestDto.setOccupyStatus(1);
        requestDto.setParkingId(36L);
        requestDto.setParkingLotId(10L);
        requestDto.setProvider(100);
        requestDto.setSerialNumber("2123465");
        magneticHeartBeatService.addMagneticHeartBeat(requestDto);
    }

    @Test
    public void testMagneticReportRecordAdd() {
        MagneticReportRecordAddRequestDto requestDto = new MagneticReportRecordAddRequestDto();
        requestDto.setTenantId(1L);
        requestDto.setParkingId(36L);
        requestDto.setParkingLotId(10L);
        requestDto.setOccupyStatus(1);
        requestDto.setProvider(100);
        requestDto.setDetectorId(1L);
        requestDto.setSerialNumber("2123465");
        requestDto.setChangeTime(new Date());
        requestDto.setChangeType(1);
        magneticHeartBeatService.magneticReportRecordAdd(requestDto);
    }

    @Test
    public void testGetMagneticReportRecordPageList() {
        MagneticReportRecordQueryPageRequestDto resultRequestDto = new MagneticReportRecordQueryPageRequestDto();
        resultRequestDto.setAreaCode("4401000");
        resultRequestDto.setParkingName("富阳巨力停车场");
        resultRequestDto.setLotType("1");
        resultRequestDto.setBeginTime(new Date());
        PagedResultDto<MagneticReportRecordQueryPageResultDto> magneticReportRecordPageList = magneticHeartBeatService.getMagneticReportRecordPageList(resultRequestDto);
        assertTrue(magneticReportRecordPageList.isSuccess());
        List<MagneticReportRecordQueryPageResultDto> items = magneticReportRecordPageList.getItems();
        assertNotNull(items);
    }

    @Test
    public void testAddMagneticStatusRecord() {
        MagneticStatusRecordAddRequestDto requestDto = new MagneticStatusRecordAddRequestDto();
        requestDto.setTenantId(1L);
        requestDto.setParkingId(36L);
        requestDto.setParkingLotId(10L);
        requestDto.setDetectorId(1L);
        requestDto.setOccupyStatus(1);
        requestDto.setProvider(100);
        requestDto.setSerialNumber("2123465");
        requestDto.setChangeTime(new Date());
        requestDto.setChangeType(1);
        magneticHeartBeatService.addMagneticStatusRecord(requestDto);
    }

    @Test
    public void testGetLastMagneticHeartBeat() {
        GetLastMagneticHeartBeatRequestDto requestDto = new GetLastMagneticHeartBeatRequestDto();
        requestDto.setDetectorId(1L);
        requestDto.setProvider(100);
        ObjectResultDto<GetLastMagneticHeartBeatAddResultDto> lastMagneticHeartBeat = magneticHeartBeatService.getLastMagneticHeartBeat(requestDto);
        assertTrue(lastMagneticHeartBeat.isSuccess());
        GetLastMagneticHeartBeatAddResultDto data = lastMagneticHeartBeat.getData();
        assertNotNull(data);
    }
}
