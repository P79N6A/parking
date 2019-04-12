package com.zoeeasy.cloud.charge.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.charge.chg.ChargeRuleService;
import com.zoeeasy.cloud.charge.chg.dto.request.*;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleViewResultDto;
import com.zoeeasy.cloud.charge.enums.ChargeRuleTypeEnum;
import com.zoeeasy.cloud.charge.enums.HolidayTypeEnum;
import com.zoeeasy.cloud.core.enums.CarTypeEnum;
import com.zoeeasy.cloud.core.enums.ParkingLevelEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * @author AkeemSuper
 * @date 2018/11/14 0014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
public class ChargeRuleServiceTest {

    @Autowired
    private ChargeRuleService chargeRuleService;

    @Test
    public void addChargeRule() {
        ChargeRuleAddRequestDto chargeRuleAddRequestDto = new ChargeRuleAddRequestDto();
        chargeRuleAddRequestDto.setCarType(CarTypeEnum.OTHER_CAR.getValue());
        chargeRuleAddRequestDto.setCode("");
        chargeRuleAddRequestDto.setDayTopAmount(50);
        chargeRuleAddRequestDto.setExistPartTime(Boolean.FALSE);
        chargeRuleAddRequestDto.setHolidayType(HolidayTypeEnum.WORK_DAY.getValue());
        chargeRuleAddRequestDto.setName("测试收费规则");
        chargeRuleAddRequestDto.setParkingLevel(ParkingLevelEnum.EQUALLY.getValue());
        chargeRuleAddRequestDto.setRuleType(ChargeRuleTypeEnum.GIST_TO_FREE.getValue());
        ResultDto resultDto = chargeRuleService.addChargeRule(chargeRuleAddRequestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void getChargeRule() {
        ChargeRuleGetRequestDto chargeRuleGetRequestDto = new ChargeRuleGetRequestDto();
        chargeRuleGetRequestDto.setId(1L);
        ObjectResultDto<ChargeRuleViewResultDto> chargeRule = chargeRuleService.getChargeRule(chargeRuleGetRequestDto);
        assertTrue(chargeRule.isSuccess());
    }

    @Test
    public void updateChargeRule() {
        ChargeRuleUpdateRequestDto chargeRuleUpdateRequestDto = new ChargeRuleUpdateRequestDto();
        chargeRuleUpdateRequestDto.setId(1L);
        chargeRuleUpdateRequestDto.setCarType(CarTypeEnum.OTHER_CAR.getValue());
        chargeRuleUpdateRequestDto.setDayTopAmount(50);
        chargeRuleUpdateRequestDto.setExistPartTime(Boolean.FALSE);
        chargeRuleUpdateRequestDto.setHolidayType(HolidayTypeEnum.WORK_DAY.getValue());
        chargeRuleUpdateRequestDto.setName("测试收费规则");
        chargeRuleUpdateRequestDto.setParkingLevel(ParkingLevelEnum.EQUALLY.getValue());
        chargeRuleUpdateRequestDto.setRuleType(ChargeRuleTypeEnum.GIST_TO_FREE.getValue());
        ResultDto resultDto = chargeRuleService.updateChargeRule(chargeRuleUpdateRequestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void getChargeRulePageList() {
        ChargeRuleGetPageListRequestDto chargeRuleGetPageListRequestDto = new ChargeRuleGetPageListRequestDto();
        PagedResultDto<ChargeRuleViewResultDto> chargeRulePageList = chargeRuleService.getChargeRulePageList(chargeRuleGetPageListRequestDto);
        assertTrue(chargeRulePageList.isSuccess());
    }

    @Test
    public void getChargeRuleTypeComboboxList() {
        ListResultDto<ComboboxItemDto> chargeRuleTypeComboboxList = chargeRuleService.getChargeRuleTypeComboboxList(new GetChargeRuleTypeRequestDto());
        assertTrue(chargeRuleTypeComboboxList.isSuccess());

    }

    @Test
    public void getUnitTime() {
        ListResultDto<ComboboxItemDto> unitTime = chargeRuleService.getUnitTime(new GetUnitTimeRequestDto());
        assertTrue(unitTime.isSuccess());
    }

    @Test
    public void deleteChargeRule() {
        ChargeRuleDeleteRequestDto chargeRuleDeleteRequestDto = new ChargeRuleDeleteRequestDto();
        chargeRuleDeleteRequestDto.setDeleteId(1L);
        ResultDto resultDto = chargeRuleService.deleteChargeRule(chargeRuleDeleteRequestDto);
        assertTrue(resultDto.isSuccess());
    }
}
