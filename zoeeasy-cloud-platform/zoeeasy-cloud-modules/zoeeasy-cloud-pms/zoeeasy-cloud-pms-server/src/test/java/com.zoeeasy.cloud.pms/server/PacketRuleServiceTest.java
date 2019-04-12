package com.zoeeasy.cloud.pms.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.PacketRuleService;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketRuleListGetResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketRuleQueryPagedResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketRuleResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
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
 * @date: 2018/10/20.
 * @author：zm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
@Transactional
public class PacketRuleServiceTest {

    @Autowired
    private PacketRuleService packetRuleService;

    @Test
    public void testAddPacketRule() {
        PacketRuleAddRequestDto requestDto = new PacketRuleAddRequestDto();
        requestDto.setPacketName("包月");
        requestDto.setPacketType(1);
        requestDto.setAllParking(1);
        requestDto.setPlateColor(3);
        requestDto.setPrice(150);
        List<Long> parkingIds = new ArrayList<>();
        parkingIds.add(2L);
        parkingIds.add(3L);
        requestDto.setParkingIds(parkingIds);
        ResultDto resultDto = packetRuleService.addPacketRule(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testUpdatePacketRule() {
        PacketRuleUpdateRequestDto requestDto = new PacketRuleUpdateRequestDto();
        requestDto.setId(2L);
        requestDto.setPacketName("包月");
        requestDto.setPacketType(2);
        requestDto.setPlateColor(3);
        requestDto.setPrice(150);
        ResultDto resultDto = packetRuleService.updatePacketRule(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testDeletePacketRule() {
        PacketRuleDeleteRequestDto requestDto = new PacketRuleDeleteRequestDto();
        requestDto.setId(2L);
        ResultDto resultDto = packetRuleService.deletePacketRule(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testGetPacketRule() {
        PacketRuleGetRequestDto requestDto = new PacketRuleGetRequestDto();
        requestDto.setId(2L);
        ObjectResultDto<PacketRuleResultDto> resultDto = packetRuleService.getPacketRule(requestDto);
        PacketRuleResultDto data = resultDto.getData();
        assertTrue(resultDto.isSuccess());
        assertNotNull(data);
    }

    @Test
    public void testGetPacketRuleList() {
        PacketRuleListGetRequestDto requestDto = new PacketRuleListGetRequestDto();
        requestDto.setPacketType(1);
        requestDto.setAreaCode("4401000");
        requestDto.setLotType("1");
        ListResultDto<PacketRuleListGetResultDto> resultDto = packetRuleService.getPacketRuleList(requestDto);
        List<PacketRuleListGetResultDto> list = resultDto.getItems();
        assertTrue(resultDto.isSuccess());
        assertNotNull(list);
    }

    @Test
    public void testGetPacketRulePagedList() {
        PacketRuleQueryPagedRequestDto requestDto = new PacketRuleQueryPagedRequestDto();
        requestDto.setPageNo(1);
        requestDto.setPageSize(10);
        requestDto.setPacketType(1);
        requestDto.setPacketName("包月");
        requestDto.setParkingName("2号停车场");
        PagedResultDto<PacketRuleQueryPagedResultDto> packetRulePagedList = packetRuleService.getPacketRulePagedList(requestDto);
        List<PacketRuleQueryPagedResultDto> items = packetRulePagedList.getItems();
        assertTrue(packetRulePagedList.isSuccess());
        assertNotNull(items);
    }

}
