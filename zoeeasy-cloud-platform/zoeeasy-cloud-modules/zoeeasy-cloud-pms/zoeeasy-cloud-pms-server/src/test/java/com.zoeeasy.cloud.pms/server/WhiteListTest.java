package com.zoeeasy.cloud.pms.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.WhiteListService;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.WhiteListQueryPagedResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.WhiteListResultDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @date: 2018/10/20.
 * @author：zm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
@Transactional
public class WhiteListTest {

    @Autowired
    private WhiteListService whiteListService;

    @Test
    public void testAddWhiteList() {
        WhiteListAddRequestDto requestDto = new WhiteListAddRequestDto();
        requestDto.setPlateType("其他");
        requestDto.setCarColor(3);
        requestDto.setCarType(2);
        requestDto.setOwnerName("逐一科技");
        requestDto.setOwnerPhone("15924182806");
        requestDto.setParkingId(2L);
        requestDto.setPlateColor(2);
        requestDto.setPlateNumber("浙A7553");
        requestDto.setBeginTime(new Date());
        requestDto.setEndTime(new Date());
        ResultDto resultDto = whiteListService.addWhiteList(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testUpdateWhiteList() {
        WhiteListUpdateRequestDto request = new WhiteListUpdateRequestDto();
        request.setCarColor(1);
        request.setCarType(3);
        request.setPlateType("教练车");
        request.setPlateColor(2);
        request.setOwnerName("逐一");
        request.setOwnerPhone("15939133261");
        request.setEndTime(new Date());
        ResultDto resultDto = whiteListService.updateWhiteList(request);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testDeleteWhiteList() {
        WhiteListDeleteRequestDto request = new WhiteListDeleteRequestDto();
        request.setId(2L);
        ResultDto resultDto = whiteListService.deleteWhiteList(request);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testGetWhiteList() {
        WhiteListGetRequestDto requestDto = new WhiteListGetRequestDto();
        requestDto.setId(2L);
        ObjectResultDto<WhiteListResultDto> resultDto = whiteListService.getWhiteList(requestDto);
        WhiteListResultDto data = resultDto.getData();
        assertTrue(resultDto.isSuccess());
        assertNotNull(data);
    }

    @Test
    public void testGetWhiteListPagedList() {
        WhiteListQueryPagedRequestDto request = new WhiteListQueryPagedRequestDto();
        request.setAreaCode("22");
        request.setParkingName("停车场");
        request.setPlateNumber("浙A7541");
        request.setStatus(1);
        request.setPageNo(1);
        request.setPageSize(10);
        PagedResultDto<WhiteListQueryPagedResultDto> resultDto = whiteListService.getWhiteListPagedList(request);
        List<WhiteListQueryPagedResultDto> items = resultDto.getItems();
        assertTrue(resultDto.isSuccess());
        assertNotNull(items);
    }

}
