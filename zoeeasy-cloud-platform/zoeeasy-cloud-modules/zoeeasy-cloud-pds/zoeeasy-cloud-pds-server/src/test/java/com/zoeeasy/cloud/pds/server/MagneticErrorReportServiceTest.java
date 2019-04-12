package com.zoeeasy.cloud.pds.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zoeeasy.cloud.pds.magneticerrorreport.MagneticErrorReportService;
import com.zoeeasy.cloud.pds.magneticerrorreport.dto.request.MagneticErrorReportListResultRequestDto;
import com.zoeeasy.cloud.pds.magneticerrorreport.dto.request.MagneticErrorReportQueryPagedResultRequestDto;
import com.zoeeasy.cloud.pds.magneticerrorreport.dto.result.MagneticErrorReportResultDto;
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
public class MagneticErrorReportServiceTest {

    @Autowired
    private MagneticErrorReportService magneticErrorReportService;

    @Test
    public void testGetMagneticErrorReportList() {
        MagneticErrorReportListResultRequestDto resultRequestDto = new MagneticErrorReportListResultRequestDto();
        resultRequestDto.setAreaCode("4401000");
        resultRequestDto.setEndTime(new Date());
        ListResultDto<MagneticErrorReportResultDto> magneticErrorReportList = magneticErrorReportService.getMagneticErrorReportList(resultRequestDto);
        assertTrue(magneticErrorReportList.isSuccess());
        List<MagneticErrorReportResultDto> items = magneticErrorReportList.getItems();
        assertNotNull(items);
    }

    @Test
    public void testGetMagneticErrorReportPagedList() {
        MagneticErrorReportQueryPagedResultRequestDto requestDto = new MagneticErrorReportQueryPagedResultRequestDto();
        requestDto.setAreaCode("4401000");
        requestDto.setEndTime(new Date());
        PagedResultDto<MagneticErrorReportResultDto> magneticErrorReportPagedList = magneticErrorReportService.getMagneticErrorReportPagedList(requestDto);
        assertTrue(magneticErrorReportPagedList.isSuccess());
        List<MagneticErrorReportResultDto> items = magneticErrorReportPagedList.getItems();
        assertNotNull(items);
    }
}
