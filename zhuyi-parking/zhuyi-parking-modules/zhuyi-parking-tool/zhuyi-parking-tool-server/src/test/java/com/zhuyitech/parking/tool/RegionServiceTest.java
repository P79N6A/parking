/*
package com.zhuyitech.parking.tool;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.dubbo.starter.QuickDubboBootstrap;
import com.scapegoat.infrastructure.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.zhuyitech.parking.tool.dto.request.region.*;
import com.zhuyitech.parking.tool.dto.result.region.RegionResultDto;
import com.zhuyitech.parking.tool.dto.result.region.RegionViewResultDto;
import com.zhuyitech.parking.tool.service.api.RegionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboBootstrap.class)
public class RegionServiceTest {

    @Autowired
    private RegionService regionService;

    */
/**
     * @return
     *//*

    @Test
    public void testGetRegionById() {

        RegionGetRequestDto getRegionDto = new RegionGetRequestDto();
        getRegionDto.setId(13L);
        ObjectResultDto<RegionResultDto> objectResultDto = regionService.getRegion(getRegionDto);
        assertTrue(objectResultDto.isSuccess());
        assertNotNull(objectResultDto.getData());
        assertEquals(objectResultDto.getData().getId().longValue(), 13);
    }

    @Test
    public void testGetRegionListByParentId() {

        RegionListGetRequestDto getRegionDto = new RegionListGetRequestDto();
        getRegionDto.setId(13L);
        ListResultDto<RegionResultDto> listResultDto = regionService.getRegionList(getRegionDto);
        assertTrue(listResultDto.isSuccess());
        assertNotNull(listResultDto.getItems());
    }

    @Test
    public void testGetPagedRegionListByParentId() {

        RegionQueryPagedResultRequestDto getRegionDto = new RegionQueryPagedResultRequestDto();
        getRegionDto.setParentId(13L);
        PagedResultDto<RegionResultDto> pagedResultDto = regionService.getRegionPagedList(getRegionDto);
        assertTrue(pagedResultDto.isSuccess());
        assertNotNull(pagedResultDto.getItems());
    }

    @Test
    public void testGetProvinceList() {
        ProvinceListGetRequestDto provinceListGetRequestDto = new ProvinceListGetRequestDto();
        ListResultDto<RegionViewResultDto> listResultDto = regionService.getProvinceList(provinceListGetRequestDto);
        assertTrue(listResultDto.isSuccess());
        assertNotNull(listResultDto.getItems());
    }

    @Test
    public void testGetCityList() {

        CityListGetRequestDto cityListGetRequestDto = new CityListGetRequestDto();
        cityListGetRequestDto.setProvinceCode("330000");
        ListResultDto<RegionViewResultDto> listResultDto = regionService.getCityList(cityListGetRequestDto);
        assertTrue(listResultDto.isSuccess());
        assertNotNull(listResultDto.getItems());
    }

    @Test
    public void testGetCountyList() {
        CountyListGetRequestDto countyListGetRequestDto = new CountyListGetRequestDto();
        countyListGetRequestDto.setCityCode("330100");
        ListResultDto<RegionViewResultDto> listResultDto = regionService.getCountyList(countyListGetRequestDto);
        assertTrue(listResultDto.isSuccess());
        assertNotNull(listResultDto.getItems());
    }
}
*/
