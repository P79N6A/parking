package com.zoeeasy.cloud.pms.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.BlackListService;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.BlackListGetResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.BlackListPagedResultDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Created by song on 2018/10/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
@Transactional
public class BlackListServiceTest {

    @Autowired
    private BlackListService blackListService;

    @Test
    public void testAddBlackList() {
        BlackListAddRequestDto requestDto = new BlackListAddRequestDto();
        requestDto.setPlateNumber("京A8888");
        requestDto.setPlateColor(1);
        requestDto.setOwnerName("xcc");
        requestDto.setOwnerPhone("15375183535");
        requestDto.setBeginTime(new Date());
        requestDto.setEndTime(new Date());
        requestDto.setParkingId(36L);
        requestDto.setRemark("哈哈哈");
        ResultDto resultDto = blackListService.addBlackList(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testUpdateBlackList() {
        BlackListUpdateRequestDto requestDto = new BlackListUpdateRequestDto();
        requestDto.setId(31L);
        requestDto.setPlateColor(1);
        requestDto.setOwnerName("xcc");
        requestDto.setOwnerPhone("15375183535");
//        requestDto.setBeginTime(new Date());
        requestDto.setEndTime(new Date());
//        requestDto.setParkingId(36L);
        requestDto.setRemark("备注哈哈哈");
        ResultDto resultDto = blackListService.updateBlackList(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testGetBlackList() {
        BlackListGetRequestDto requestDto = new BlackListGetRequestDto();
        requestDto.setId(31L);
        ObjectResultDto<BlackListGetResultDto> objectResultDto = blackListService.getBlackList(requestDto);
        assertTrue(objectResultDto.isSuccess());
    }

    @Test
    public void testDeleteBlackList() {
        BlackListDeleteRequestDto requestDto = new BlackListDeleteRequestDto();
        requestDto.setId(31L);
        ResultDto resultDto = blackListService.deleteBlackList(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testGetBlackListPagedList() {
        BlackListQueryPagedRequestDto requestDto = new BlackListQueryPagedRequestDto();
        requestDto.setPageNo(1);
        requestDto.setPageSize(1);
        requestDto.setPlateColor(1);
        requestDto.setPlateNumber("浙A753");
        requestDto.setParkingName("停车场");
        requestDto.setStatus(1);
        PagedResultDto<BlackListPagedResultDto> pagedResultDto = blackListService.getBlackListPagedList(requestDto);
        assertTrue(pagedResultDto.isSuccess());
    }

}
