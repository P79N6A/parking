/*
package com.zhuyitech.parking.pms;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.dubbo.starter.QuickDubboBootstrap;
import com.scapegoat.infrastructure.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.zhuyitech.parking.pms.dto.request.park.*;
import com.zhuyitech.parking.pms.dto.result.park.ParkingResultDto;
import com.zhuyitech.parking.pms.service.api.ParkingInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboBootstrap.class)
public class ParkingAppServiceTest {

    @Autowired
    private ParkingInfoService parkingAppService;

    @Test
    public void parkingAddTest() {
        ParkingAddRequestDto parkingAddRequestDto = new ParkingAddRequestDto();
        parkingAddRequestDto.setCode("parkingAddRequestDto");
        parkingAddRequestDto.setName("测试菜单项");
        parkingAddRequestDto.setFullName("测试菜单项");
        parkingAddRequestDto.setType("2");
        parkingAddRequestDto.setOpenStartTime("08:00");
        parkingAddRequestDto.setOpenEndTime("20:00");
        parkingAddRequestDto.setChargeDescription("15分钟免费,5元/小时");
        parkingAddRequestDto.setProvinceCode("330000");
        parkingAddRequestDto.setCityCode("330100");
        parkingAddRequestDto.setCountyCode("330108");
        parkingAddRequestDto.setLatitude(120.218343);
        parkingAddRequestDto.setLongitude(30.214683);
        parkingAddRequestDto.setAddress("江南大道100号");
        parkingAddRequestDto.setZipCode("310051");
        parkingAddRequestDto.setContactTel("(0571)87702007");
        parkingAddRequestDto.setContactName("联系人");
        parkingAddRequestDto.setChargerUnit("收费单位");
        parkingAddRequestDto.setManagerUnit("管理单位");
        parkingAddRequestDto.setOperatorUnit("运营单位");
        parkingAddRequestDto.setOwnerName("所有人单位");
        parkingAddRequestDto.setDescription("测试停车场");
        ResultDto resultDto = parkingAppService.addParking(parkingAddRequestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void parkingGetTest() {

        ParkingGetRequestDto parkingGetRequestDto = new ParkingGetRequestDto();
        parkingGetRequestDto.setId(1L);
        ObjectResultDto<ParkingResultDto> resultDto = parkingAppService.getParking(parkingGetRequestDto);
        assertTrue(resultDto.isSuccess());
        assertNotNull(resultDto.getData());
    }

    @Test
    public void parkingUpdateTest() {

        ParkingGetRequestDto parkingGetRequestDto = new ParkingGetRequestDto();
        parkingGetRequestDto.setId(1L);
        ObjectResultDto<ParkingResultDto> resultDto = parkingAppService.getParking(parkingGetRequestDto);
        assertTrue(resultDto.isSuccess());
        assertNotNull(resultDto.getData());
        if (resultDto.getData() != null) {

            ParkingResultDto parkingResultDto = resultDto.getData();
            ParkingUpdateRequestDto parkingUpdateRequestDto = new ParkingUpdateRequestDto();
            parkingUpdateRequestDto.setId(parkingResultDto.getId());
            parkingUpdateRequestDto.setCode("testMenuItemUpdated");
            parkingUpdateRequestDto.setName("测试菜单项修改");
            parkingUpdateRequestDto.setFullName("测试菜单项修改");
            parkingUpdateRequestDto.setStatus(2);
            parkingUpdateRequestDto.setType("2");
            parkingUpdateRequestDto.setOpenStartTime("00:00");
            parkingUpdateRequestDto.setOpenEndTime("23:59");
            parkingUpdateRequestDto.setChargeDescription("15分钟免费,8元/小时");
            ResultDto resultDtoUpdate = parkingAppService.updateParking(parkingUpdateRequestDto);
            assertTrue(resultDtoUpdate.isSuccess());
        }
    }

    @Test
    public void parkingListGetTest() {

        ParkingListGetRequestDto parkingListGetRequestDto = new ParkingListGetRequestDto();
        ListResultDto<ParkingResultDto> listResultDto = parkingAppService.getParkingList(parkingListGetRequestDto);
        assertTrue(listResultDto.isSuccess());
        assertFalse(listResultDto.getItems().isEmpty());
    }

    @Test
    public void parkingPagedListGetTest() {
        ParkingQueryPagedResultRequestDto parkingQueryPagedResultRequestDto = new ParkingQueryPagedResultRequestDto();
        PagedResultDto<ParkingResultDto> pagedResultDto = parkingAppService.getParkingPagedList(parkingQueryPagedResultRequestDto);
        assertTrue(pagedResultDto.isSuccess());
        assertFalse(pagedResultDto.getItems().isEmpty());
    }

    @Test
    public void parkingDeleteTest() {

        ParkingDeleteRequestDto requestDto = new ParkingDeleteRequestDto();
        requestDto.setId(1L);
        ResultDto resultDto = parkingAppService.deleteParking(requestDto);
        assertTrue(resultDto.isSuccess());

        ParkingGetRequestDto parkingGetRequestDto = new ParkingGetRequestDto();
        parkingGetRequestDto.setId(1L);
        ObjectResultDto<ParkingResultDto> resultDto2 = parkingAppService.getParking(parkingGetRequestDto);
        assertTrue(resultDto2.isSuccess());
    }
}
*/
