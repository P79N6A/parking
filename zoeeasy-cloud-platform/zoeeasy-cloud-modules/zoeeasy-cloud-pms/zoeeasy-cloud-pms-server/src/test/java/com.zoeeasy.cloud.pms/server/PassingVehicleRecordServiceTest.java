package com.zoeeasy.cloud.pms.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.passing.PassingVehicleRecordService;
import com.zoeeasy.cloud.pms.passing.dto.request.PassVehicleRecordAddRemarkRequestDto;
import com.zoeeasy.cloud.pms.passing.dto.request.PassVehicleRecordProofreadRequestDto;
import com.zoeeasy.cloud.pms.passing.dto.request.PassingVehicleRecordGetByPassNoRequestDto;
import com.zoeeasy.cloud.pms.passing.dto.request.PassingVehicleRecordQueryPageRequestDto;
import com.zoeeasy.cloud.pms.passing.dto.result.PassingVehicleRecordViewResultDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * @author AkeemSuper
 * @date 2018/9/17 0017
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
public class PassingVehicleRecordServiceTest {

    @Autowired
    private PassingVehicleRecordService passingVehicleRecordService;

    /**
     * 分页获取过车记录
     */
    @Test
    public void getPassVehicleRecordListPage() {
        PassingVehicleRecordQueryPageRequestDto passingVehicleRecordQueryPageRequestDto = new PassingVehicleRecordQueryPageRequestDto();
        PagedResultDto<PassingVehicleRecordViewResultDto> passVehicleRecordListPage = passingVehicleRecordService.getPassVehicleRecordListPage(passingVehicleRecordQueryPageRequestDto);
        assertTrue(passVehicleRecordListPage.isSuccess());
    }

    /**
     * 根据过车记录号获取过车记录
     */
    @Test
    public void getPassRecordByPassNo() {
        PassingVehicleRecordGetByPassNoRequestDto passingVehicleRecordGetByPassNoRequestDto = new PassingVehicleRecordGetByPassNoRequestDto();
        passingVehicleRecordGetByPassNoRequestDto.setPassingNo("11000620181025214651736365620918");
        ObjectResultDto<PassingVehicleRecordViewResultDto> vehicleRecordServicePassRecordByPassNo = passingVehicleRecordService.getPassRecordByPassNo(passingVehicleRecordGetByPassNoRequestDto);
        assertTrue(vehicleRecordServicePassRecordByPassNo.isSuccess());
    }

    /**
     * 人工校对过车记录
     */
    @Test
    public void proofreadPassVehicleRecord() {
        PassVehicleRecordProofreadRequestDto passVehicleRecordProofreadRequestDto = new PassVehicleRecordProofreadRequestDto();
        passVehicleRecordProofreadRequestDto.setPassingNo("11000620181025214651736365620918");
        ResultDto resultDto = passingVehicleRecordService.proofreadPassVehicleRecord(passVehicleRecordProofreadRequestDto);
        assertTrue(resultDto.isSuccess());
    }

    /**
     * 添加过车记录备注
     */
    @Test
    public void addPassVehicleRecordRemark() {
        PassVehicleRecordAddRemarkRequestDto passVehicleRecordAddRemarkRequestDto = new PassVehicleRecordAddRemarkRequestDto();
        passVehicleRecordAddRemarkRequestDto.setPassingNo("11000620181025214651736365620918");
        passVehicleRecordAddRemarkRequestDto.setRemark("备注");
        ResultDto resultDto = passingVehicleRecordService.addPassVehicleRecordRemark(passVehicleRecordAddRemarkRequestDto);
        assertTrue(resultDto.isSuccess());
    }
}
