package com.zoeeasy.cloud.pms.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.*;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingListGetResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingListResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingResultDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by song on 2018/10/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
@Transactional
public class ParkingInfoServiceTest {

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Test
    public void testAddParking() {
        ParkingAddRequestDto requestDto = new ParkingAddRequestDto();
        requestDto.setCode("9763541");
        requestDto.setAreaCode("440000");
        requestDto.setFullName("浙江一号停车场");
        requestDto.setLotType("1");
        requestDto.setOutTime(30);
        requestDto.setLotTotal(100);
        requestDto.setLotAvailable(90);
        requestDto.setLotFixed(50);
        requestDto.setLatitude(103.5d);
        requestDto.setLongitude(108.5d);
        requestDto.setAddress("浙江省杭州市富阳区巨利新村");
        requestDto.setZipCode("246420");
        requestDto.setDescription("就是一个停车场啊");
        requestDto.setOpenStartTime("08:00:00");
        requestDto.setOpenEndTime("23:00:00");
        requestDto.setChargeFee(0);
        requestDto.setPayMode("1");
        requestDto.setChargeMode(2);
        requestDto.setPayType("3");
        requestDto.setFreeTime(30);
        requestDto.setChargeDescription("暂无");
        requestDto.setSupportAppointment(1);
        requestDto.setLotAppointmentTotal(90);
        requestDto.setLotAppointmentAvailable(45);
        requestDto.setAppointmentRule("暂无");
        requestDto.setContactName("xcc");
        requestDto.setContactTel("0456-7488965");
        requestDto.setContactPhone("15375183535");
        requestDto.setContactWeixin("15375183535");
        requestDto.setContactQQ("1197308689");
        requestDto.setContactAlipay("15375183535");
        requestDto.setContactEmail("1197308689@qq.com");
        requestDto.setManagerUnit("逐一科技");
        requestDto.setOwnerName("逐一科技");
        requestDto.setOperatorUnit("逐一科技");
        requestDto.setChargerUnit("逐一科技");
        requestDto.setLogo("logo");
        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("tinche1.png");
        imageUrls.add("tinche2.png");
        requestDto.setImageUrls(imageUrls);
        ResultDto resultDto = parkingInfoService.addParking(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testUpdateParking() {
        ParkingUpdateRequestDto requestDto = new ParkingUpdateRequestDto();
        requestDto.setId(2L);
        requestDto.setAreaCode("440000");
        requestDto.setLotType("1");
        requestDto.setOutTime(30);
        requestDto.setLotTotal(100);
        requestDto.setLotAvailable(90);
        requestDto.setLotFixed(50);
        requestDto.setLatitude(103.5d);
        requestDto.setLongitude(108.5d);
        requestDto.setAddress("浙江省杭州市富阳区巨利新村");
        requestDto.setZipCode("246420");
        requestDto.setDescription("就是一个停车场啊");
        requestDto.setOpenStartTime("08:00:00");
        requestDto.setOpenEndTime("23:00:00");
        requestDto.setChargeFee(0);
        requestDto.setPayMode("1");
        requestDto.setChargeMode(2);
        requestDto.setPayType("3");
        requestDto.setFreeTime(30);
        requestDto.setChargeDescription("暂无");
        requestDto.setSupportAppointment(1);
        requestDto.setLotAppointmentTotal(90);
        requestDto.setLotAppointmentAvailable(45);
        requestDto.setAppointmentRule("暂无");
        requestDto.setContactName("xcc");
        requestDto.setContactTel("0456-7488965");
        requestDto.setContactPhone("15375183535");
        requestDto.setContactWeixin("15375183535");
        requestDto.setContactQQ("1197308689");
        requestDto.setContactAlipay("15375183535");
        requestDto.setContactEmail("1197308689@qq.com");
        requestDto.setManagerUnit("逐一科技");
        requestDto.setOwnerName("逐一科技");
        requestDto.setOperatorUnit("逐一科技");
        requestDto.setChargerUnit("逐一科技");
        requestDto.setLogo("logo");
        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("tinche1.png");
        imageUrls.add("tinche2.png");
        requestDto.setImageUrls(imageUrls);
        ResultDto resultDto = parkingInfoService.updateParking(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testDeleteParking() {
        ParkingDeleteRequestDto requestDto = new ParkingDeleteRequestDto();
        requestDto.setId(2L);
        ResultDto resultDto = parkingInfoService.deleteParking(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testGetParking() {
        ParkingGetRequestDto requestDto = new ParkingGetRequestDto();
        requestDto.setId(2L);
        ObjectResultDto<ParkingResultDto> resultDto = parkingInfoService.getParking(requestDto);
        ParkingResultDto parkingResultDto = resultDto.getData();
        assertTrue(resultDto.isSuccess());
        assertNotNull(parkingResultDto);
    }

    @Test
    public void testGetParkingPagedList() {
        ParkingQueryPagedRequestDto requestDto = new ParkingQueryPagedRequestDto();
        requestDto.setPageNo(1);
        requestDto.setPageSize(10);
        requestDto.setAreaCode("440000");
        requestDto.setFullName("2号停车场");
        requestDto.setCode("2456753");
        requestDto.setLotType("1");
        PagedResultDto<ParkingListResultDto> resultDto = parkingInfoService.getParkingPagedList(requestDto);
        List<ParkingListResultDto> list = resultDto.getItems();
        assertTrue(resultDto.isSuccess());
        assertNotNull(list);
    }

    @Test
    public void testGetParkingList() {
        ParkingListGetRequestDto requestDto = new ParkingListGetRequestDto();
        requestDto.setAreaCode("440000");
        requestDto.setName("2号停车场");
        requestDto.setCode("2456753");
        requestDto.setLotType("1");
        ListResultDto<ParkingListGetResultDto> resultDto = parkingInfoService.getParkingList(requestDto);
        List<ParkingListGetResultDto> list = resultDto.getItems();
        assertTrue(resultDto.isSuccess());
        assertNotNull(list);
    }

}
