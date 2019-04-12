package com.zoeeasy.cloud.pms.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.PacketApproveService;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketApproveGetResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.PacketApproveResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertTrue;

/**
 * Created by song on 2018/10/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
@Transactional
public class PacketApproveServiceTest {

    @Autowired
    private PacketApproveService packetApproveService;

    @Test
    public void testApplyCancelPacket() {
        ApplyCancelPacketRequestDto requestDto = new ApplyCancelPacketRequestDto();
        requestDto.setId(47L);
        ResultDto resultDto = packetApproveService.applyCancelPacket(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testGetPacketApprove() {
        PacketApproveGetRequestDto requestDto = new PacketApproveGetRequestDto();
        requestDto.setId(47L);
        ObjectResultDto<PacketApproveGetResultDto> objectResultDto = packetApproveService.getPacketApprove(requestDto);
        assertTrue(objectResultDto.isSuccess());
    }

    @Test
    public void testCheckPacketApprove() {
        CheckPacketApproveRequestDto requestDto = new CheckPacketApproveRequestDto();
        requestDto.setId(47L);
        requestDto.setApproveStatus(2);
        ResultDto resultDto = packetApproveService.checkPacketApprove(requestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void testGetPacketApprovePagedList() {
        CancelPacketApplyQueryPagedRequestDto requestDto = new CancelPacketApplyQueryPagedRequestDto();
        requestDto.setPageNo(1);
        requestDto.setPageSize(1);
        requestDto.setApproveStatus(0);
        requestDto.setPlateNumber("皖A7823");
        requestDto.setParkingName("停车场");
        PagedResultDto<PacketApproveResultDto> pagedResultDto = packetApproveService.getPacketApprovePagedList(requestDto);
        assertTrue(pagedResultDto.isSuccess());
    }

    @Test
    public void testDeletePacketApprove() {
        PacketApproveDeleteRequestDto requestDto = new PacketApproveDeleteRequestDto();
        requestDto.setId(3L);
        ResultDto resultDto = packetApproveService.deletePacketApprove(requestDto);
        assertTrue(resultDto.isSuccess());
    }

}
