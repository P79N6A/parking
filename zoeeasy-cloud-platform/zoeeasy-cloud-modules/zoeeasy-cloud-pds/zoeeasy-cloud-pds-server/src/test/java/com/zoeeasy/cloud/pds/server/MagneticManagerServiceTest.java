package com.zoeeasy.cloud.pds.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pds.magneticmanager.MagneticManagerService;
import com.zoeeasy.cloud.pds.magneticmanager.dto.request.*;
import com.zoeeasy.cloud.pds.magneticmanager.dto.result.MagneticManagerByIdResultDto;
import com.zoeeasy.cloud.pds.magneticmanager.dto.result.MagneticManagerResultDto;
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
public class MagneticManagerServiceTest {

    @Autowired
    private MagneticManagerService magneticManagerService;

    /**
     * @return
     */
    @Test
    public void testAddMagneticManager() {
        MagneticManagerAddRequestDto requestDto = new MagneticManagerAddRequestDto();
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
        ResultDto resultDto = magneticManagerService.addMagneticManager(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testDeleteMagneticManager() {
        MagneticManagerDeleteRequestDto requestDto = new MagneticManagerDeleteRequestDto();
        requestDto.setId(1L);
        ResultDto resultDto = magneticManagerService.deleteMagneticManager(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testUpdateMagneticManager() {
        MagneticManagerUpdateRequestDto requestDto = new MagneticManagerUpdateRequestDto();
        requestDto.setId(4L);
        requestDto.setHeartBeatInterval(9);
        requestDto.setPort(1255);
        ResultDto resultDto = magneticManagerService.updateMagneticManager(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testGetMagneticManager() {
        MagneticManagerGetRequestDto requestDto = new MagneticManagerGetRequestDto();
        requestDto.setId(4L);
        ObjectResultDto<MagneticManagerByIdResultDto> magneticManager = magneticManagerService.getMagneticManager(requestDto);
        assertTrue(magneticManager.isSuccess());
        MagneticManagerByIdResultDto magneticManagerResultDto = magneticManager.getData();
        assertNotNull(magneticManagerResultDto);
    }

    @Test
    public void testGetMagneticManagerPageList() {
        MagneticManagerQueryPageRequestDto requestDto = new MagneticManagerQueryPageRequestDto();
        requestDto.setAreaCode("330100");
        requestDto.setParkingId(36L);
        PagedResultDto<MagneticManagerResultDto> magneticManagerPageList = magneticManagerService.getMagneticManagerPageList(requestDto);
        assertTrue(magneticManagerPageList.isSuccess());
        List<MagneticManagerResultDto> magneticManagerResultDtos = magneticManagerPageList.getItems();
        assertNotNull(magneticManagerResultDtos);
    }
}
