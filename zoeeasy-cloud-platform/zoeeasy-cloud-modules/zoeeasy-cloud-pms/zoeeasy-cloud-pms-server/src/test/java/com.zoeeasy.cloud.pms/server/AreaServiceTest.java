package com.zoeeasy.cloud.pms.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.area.AreaService;
import com.zoeeasy.cloud.pms.area.dto.request.*;
import com.zoeeasy.cloud.pms.area.dto.result.AreaResultDto;
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
public class AreaServiceTest {
    @Autowired
    private AreaService areaService;

    @Test
    public void testAddArea() {
        AreaAddRequestDto requestDto = new AreaAddRequestDto();
        requestDto.setCode("");
        requestDto.setName("富阳巨利新村");
        requestDto.setLevel(4);
        requestDto.setParentCode("330100");

        ResultDto resultDto = areaService.addArea(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testUpdateArea() {
        AreaUpdateRequestDto requestDto = new AreaUpdateRequestDto();
        requestDto.setCode("330100_00001");
        requestDto.setName("巨利村");

        ResultDto resultDto = areaService.updateArea(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testDeleteArea() {
        AreaDeleteRequestDto requestDto = new AreaDeleteRequestDto();
        requestDto.setCode("3303001");
        ResultDto resultDto = areaService.deleteArea(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testGetArea() {
        AreaGetRequestDto requestDto = new AreaGetRequestDto();
        requestDto.setCode("3301001");
        ObjectResultDto<AreaResultDto> resultDto = areaService.getArea(requestDto);
        AreaResultDto areaResultDto = resultDto.getData();
        assertTrue(resultDto.isSuccess());
        assertNotNull(areaResultDto);
    }

    @Test
    public void testGetAreaPagedList() {
        AreaQueryPagedResultRequestDto requestDto = new AreaQueryPagedResultRequestDto();
        requestDto.setPageNo(1);
        requestDto.setPageSize(10);
        requestDto.setParentCode("330100");
        PagedResultDto<AreaResultDto> resultDto = areaService.getAreaPagedList(requestDto);
        List<AreaResultDto> list = resultDto.getItems();
        assertTrue(resultDto.isSuccess());
        assertNotNull(list);
    }

}
