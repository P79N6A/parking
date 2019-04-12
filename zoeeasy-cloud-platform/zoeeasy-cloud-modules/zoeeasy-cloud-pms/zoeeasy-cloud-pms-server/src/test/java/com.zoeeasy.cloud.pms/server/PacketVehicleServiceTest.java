package com.zoeeasy.cloud.pms.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.PacketVehicleService;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.EndDateResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketReceiptResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketVehicleGetResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketVehiclePagedResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by song on 2018/10/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
@Transactional
public class PacketVehicleServiceTest {

    @Autowired
    private PacketVehicleService packetVehicleService;

    @Test
    public void testAddPacketVehicle() {
        PacketVehicleAddRequestDto packetVehicleAddRequestDto = new PacketVehicleAddRequestDto();
        packetVehicleAddRequestDto.setAllParking(0);
        packetVehicleAddRequestDto.setPacketType(1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Date beginDate = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        Date endDate = calendar.getTime();
        packetVehicleAddRequestDto.setBeginDate(beginDate);
        packetVehicleAddRequestDto.setEndDate(endDate);
        packetVehicleAddRequestDto.setPlateNumber("浙A5684");
        packetVehicleAddRequestDto.setCarType(1);
        packetVehicleAddRequestDto.setPlateColor(1);
        packetVehicleAddRequestDto.setPlateType("1");
        packetVehicleAddRequestDto.setCarColor(1);
        packetVehicleAddRequestDto.setCarBrand("奥迪");
        packetVehicleAddRequestDto.setOwnerName("xcc");
        packetVehicleAddRequestDto.setOwnerPhone("15375183535");
        packetVehicleAddRequestDto.setOwnerCardNo("340825199905050258");
        packetVehicleAddRequestDto.setOwnerAddress("安徽");
        packetVehicleAddRequestDto.setOwnerEmail("1197308689@qq.com");
        List<ParkingPacketRuleDto> rules = new ArrayList<>();
        ParkingPacketRuleDto parkingPacketRuleDto = new ParkingPacketRuleDto();
        parkingPacketRuleDto.setParkingId(36L);
        parkingPacketRuleDto.setRuleId(1L);
        rules.add(parkingPacketRuleDto);
        packetVehicleAddRequestDto.setRules(rules);
        ObjectResultDto<PacketReceiptResultDto> objectResultDto = packetVehicleService.addPacketVehicle(packetVehicleAddRequestDto);
        assertTrue(objectResultDto.isSuccess());
    }

    @Test
    public void testGetPacketVehiclePagedList() {
        PacketVehiclePagedRequestDto requestDto = new PacketVehiclePagedRequestDto();
        requestDto.setPageNo(1);
        requestDto.setPageSize(1);
        requestDto.setPlateNumber("皖A7823");
        requestDto.setPlateColor(1);
        requestDto.setAllParking(0);
        requestDto.setParkingName("停车场");
        requestDto.setPacketType(1);
        PagedResultDto<PacketVehiclePagedResultDto> pagedResultDto = packetVehicleService.getPacketVehiclePagedList(requestDto);
        assertTrue(pagedResultDto.isSuccess());
    }

    @Test
    public void testUpdateTopUp() {
        TopUpUpdateRequestDto requestDto = new TopUpUpdateRequestDto();
        requestDto.setId(47L);
        ResultDto resultDto = packetVehicleService.updateTopUp(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testDeletePacketVehicle() {
        PacketVehicleDeleteRequestDto requestDto = new PacketVehicleDeleteRequestDto();
        requestDto.setId(47L);
        ResultDto resultDto = packetVehicleService.deletePacketVehicle(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testGetPacketVehicle() {
        PacketVehicleGetRequestDto requestDto = new PacketVehicleGetRequestDto();
        requestDto.setId(47L);
        ObjectResultDto<PacketVehicleGetResultDto> objectResultDto = packetVehicleService.getPacketVehicle(requestDto);
        assertTrue(objectResultDto.isSuccess());
    }

    @Test
    public void testGetEndDate() {
        EndDateRequestDto endDateRequestDto = new EndDateRequestDto();
        endDateRequestDto.setBeginDate(new Date());
        endDateRequestDto.setPacketType(1);
        ObjectResultDto<EndDateResultDto> objectResultDto = packetVehicleService.getEndDate(endDateRequestDto);
        assertTrue(objectResultDto.isSuccess());
    }

}
