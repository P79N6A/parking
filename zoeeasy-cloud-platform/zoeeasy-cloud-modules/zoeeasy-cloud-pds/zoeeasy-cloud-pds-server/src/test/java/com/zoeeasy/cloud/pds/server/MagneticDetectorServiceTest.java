package com.zoeeasy.cloud.pds.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pds.device.PlatformDeviceService;
import com.zoeeasy.cloud.pds.magneticdetector.MagneticDetectorService;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.airjoy.MagneticDetectorGetByMacRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.airjoy.MagneticDetectorUpdateLastHeartbeatTimeRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.park.*;
import com.zoeeasy.cloud.pds.magneticdetector.dto.result.airjoy.MagneticDetectorGetByMacResultDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.result.park.*;
import com.zoeeasy.cloud.pds.magneticmanager.dto.request.MagneticManagerIdBatchUpdateRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
@Transactional(rollbackFor = Exception.class)
public class MagneticDetectorServiceTest {

    @Autowired
    private MagneticDetectorService magneticDetectorService;

    @Autowired
    private PlatformDeviceService platformDeviceService;

    @Test
    public void testAddMagneticDetector() {
        MagneticDetectorAddRequestDto requestDto = new MagneticDetectorAddRequestDto();
        requestDto.setParkingId(29L);
        requestDto.setProvider(100);
        requestDto.setIpAddress("192.365.256.25");
        requestDto.setHeartBeatInterval(5);
        requestDto.setInstallPosition("富阳");
        requestDto.setPort(12587);
        requestDto.setSerialNumber("h12587");
        requestDto.setSimNo("125879654125874");
        requestDto.setVersionNumber("v1");
        requestDto.setInstallationTime(new Date());
        ResultDto resultDto = magneticDetectorService.addMagneticDetector(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testUpdateMagneticDetector() {
        MagneticDetectorUpdateRequestDto requestDto = new MagneticDetectorUpdateRequestDto();
        requestDto.setId(4L);
        requestDto.setPort(125288587);
        requestDto.setVersionNumber("v3");
        ResultDto resultDto = magneticDetectorService.updateMagneticDetector(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testDeleteMagneticDetector() {
        MagneticDetectorDeleteRequestDto requestDto = new MagneticDetectorDeleteRequestDto();
        requestDto.setId(6L);
        ResultDto resultDto = magneticDetectorService.deleteMagneticDetector(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testGetMagneticDetectorById() {
        MagneticDetectorGetRequestByIdDto requestByIdDto = new MagneticDetectorGetRequestByIdDto();
        requestByIdDto.setId(10L);
        ObjectResultDto<MagneticDetectorResultDto> objectResultDto = magneticDetectorService.getMagneticDetectorById(requestByIdDto);
        assertTrue(objectResultDto.isSuccess());
        MagneticDetectorResultDto magneticDetectorResultDto = objectResultDto.getData();
        assertNotNull(magneticDetectorResultDto);
    }

    @Test
    public void testGetMagneticDetector() {
        MagneticDetectorGetRequestDto requestDto = new MagneticDetectorGetRequestDto();
        requestDto.setCode("127698679535504383");
        ObjectResultDto<MagneticDetectorByCodeResultDto> magneticDetector = magneticDetectorService.getMagneticDetector(requestDto);
        assertTrue(magneticDetector.isSuccess());
        MagneticDetectorByCodeResultDto data = magneticDetector.getData();
        assertNotNull(data);
    }

    @Test
    public void testGetMagneticDetectorPagedList() {
        MagneticDetectorQueryPagedResultRequestDto resultRequestDto = new MagneticDetectorQueryPagedResultRequestDto();
        resultRequestDto.setAreaCode("330100");
        resultRequestDto.setParkingId(36L);
        PagedResultDto<MagneticDetectorByCodeResultDto> magneticDetectorPagedList = magneticDetectorService.getMagneticDetectorPagedList(resultRequestDto);
        assertTrue(magneticDetectorPagedList.isSuccess());
        List<MagneticDetectorByCodeResultDto> items = magneticDetectorPagedList.getItems();
        assertNotNull(items);
    }

    @Test
    public void testBatchUpdateMagneticManagerId() {
        MagneticManagerIdBatchUpdateRequestDto requestDto = new MagneticManagerIdBatchUpdateRequestDto();
        List<String> list = new ArrayList<>();
        list.add("123546");
        list.add("1132");
        requestDto.setCodes(list);
        requestDto.setManagerId(7L);
        ResultDto resultDto = magneticDetectorService.batchUpdateMagneticManagerId(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testGetNotRelevanceMagneticDetectorPagedList() {
        MagneticDetectorNotRelevanceQueryPageRequestDto requestDto = new MagneticDetectorNotRelevanceQueryPageRequestDto();
        requestDto.setCode("123");
        PagedResultDto<MagneticDetectorRelevanceResultDto> notRelevanceMagneticDetectorPagedList = magneticDetectorService.getNotRelevanceMagneticDetectorPagedList(requestDto);
        assertTrue(notRelevanceMagneticDetectorPagedList.isSuccess());
        List<MagneticDetectorRelevanceResultDto> magneticDetectorNotRelevanceResultDtoList = notRelevanceMagneticDetectorPagedList.getItems();
        assertNotNull(magneticDetectorNotRelevanceResultDtoList);
    }

    @Test
    public void testGetRelevanceMagneticDetectorPagedList() {
        MagneticDetectorRelevanceQueryPageRequestDto requestDto = new MagneticDetectorRelevanceQueryPageRequestDto();
        requestDto.setManagerId(1L);
        PagedResultDto<MagneticDetectorRelevanceResultDto> relevanceMagneticDetectorPagedList = magneticDetectorService.getRelevanceMagneticDetectorPagedList(requestDto);
        assertTrue(relevanceMagneticDetectorPagedList.isSuccess());
        List<MagneticDetectorRelevanceResultDto> items = relevanceMagneticDetectorPagedList.getItems();
        assertNotNull(items);
    }

    @Test
    public void testGetMagneticDetectorStatusPagedList() {
        MagneticDetectorStatusQueryPageRequestDto requestDto = new MagneticDetectorStatusQueryPageRequestDto();
        requestDto.setAreaCode("4401000");
        requestDto.setLotType("1");
        requestDto.setParkingName("富阳巨力停车场");
        PagedResultDto<MagneticDetectorStatusQueryPageResultDto> magneticDetectorStatusPagedList = magneticDetectorService.getMagneticDetectorStatusPagedList(requestDto);
        assertTrue(magneticDetectorStatusPagedList.isSuccess());
        List<MagneticDetectorStatusQueryPageResultDto> items = magneticDetectorStatusPagedList.getItems();
        assertNotNull(items);
    }

    @Test
    public void testGetNotRelevanceParkingLotMagneticDetectorPagedList() {
        MagneticDetectorNotRelevanceParkingLotQueryPageRequestDto requestDto = new MagneticDetectorNotRelevanceParkingLotQueryPageRequestDto();
        requestDto.setCode("128621852691006463");
        PagedResultDto<MagneticDetectorRelevanceParkingLotResultDto> notRelevanceParkingLotMagneticDetectorPagedList = magneticDetectorService.getNotRelevanceParkingLotMagneticDetectorPagedList(requestDto);
        assertTrue(notRelevanceParkingLotMagneticDetectorPagedList.isSuccess());
        List<MagneticDetectorRelevanceParkingLotResultDto> items = notRelevanceParkingLotMagneticDetectorPagedList.getItems();
        assertNotNull(items);
    }

    @Test
    public void testGetRelevanceParkingLotMagneticDetectorPagedList() {
        MagneticDetectorRelevanceParkingLotQueryPageRequestDto requestDto = new MagneticDetectorRelevanceParkingLotQueryPageRequestDto();
        requestDto.setParkingId(36L);
        requestDto.setParkingLotId(10L);
        PagedResultDto<MagneticDetectorRelevanceParkingLotResultDto> relevanceParkingLotMagneticDetectorPagedList = magneticDetectorService.getRelevanceParkingLotMagneticDetectorPagedList(requestDto);
        assertTrue(relevanceParkingLotMagneticDetectorPagedList.isSuccess());
        List<MagneticDetectorRelevanceParkingLotResultDto> items = relevanceParkingLotMagneticDetectorPagedList.getItems();
        assertNotNull(items);
    }

    @Test
    public void testGetMagneticDetectorByMac() {
        MagneticDetectorGetByMacRequestDto requestDto = new MagneticDetectorGetByMacRequestDto();
        requestDto.setProvider(100);
        requestDto.setSerialNumber("996");
        ObjectResultDto<MagneticDetectorGetByMacResultDto> magneticDetectorByMac = platformDeviceService.getMagneticDetectorByMac(requestDto);
        assertTrue(magneticDetectorByMac.isSuccess());
        MagneticDetectorGetByMacResultDto magneticDetectorGetByMacResultDto = magneticDetectorByMac.getData();
        assertNotNull(magneticDetectorGetByMacResultDto);
    }

    @Test
    public void testUpdateMagneticDetectorLastHeartbeatTime() {
        MagneticDetectorUpdateLastHeartbeatTimeRequestDto requestDto = new MagneticDetectorUpdateLastHeartbeatTimeRequestDto();
        requestDto.setId(1L);
        requestDto.setLastHeartbeatTime(new Date());
        ResultDto resultDto = platformDeviceService.updateMagneticDetectorLastHeartbeatTime(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testUpdateMagneticDetectorStatus() {
        MagneticDetectorStatusUpdateRequestDto requestDto = new MagneticDetectorStatusUpdateRequestDto();
        requestDto.setStatus(2);
        ResultDto resultDto = platformDeviceService.updateMagneticDetectorStatus(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testGetMagneticDetectorList() {
        MagneticDetectorListResultRequestDto requestDto = new MagneticDetectorListResultRequestDto();
        ListResultDto<MagneticDetectorByCodeResultDto> magneticDetectorList = magneticDetectorService.getMagneticDetectorList(requestDto);
        assertTrue(magneticDetectorList.isSuccess());
        List<MagneticDetectorByCodeResultDto> items = magneticDetectorList.getItems();
        assertNotNull(items);
    }

    @Test
    public void testGetMagneticDetectorByParkingAndParkingLot() {
        MagneticDetectorCodeByParkingAndParkingLotRequestByIdDto requestByIdDto = new MagneticDetectorCodeByParkingAndParkingLotRequestByIdDto();
        requestByIdDto.setParkingLotId(10L);
        requestByIdDto.setParkingId(36L);
        ObjectResultDto<MagneticDetectorByParkingAndParkingLotResultDto> parkingLot = magneticDetectorService.getMagneticDetectorByParkingAndParkingLot(requestByIdDto);
        assertTrue(parkingLot.isSuccess());
        MagneticDetectorByParkingAndParkingLotResultDto data = parkingLot.getData();
        assertNotNull(data);
    }

}
