package com.zoeeasy.cloud.charge.server;

import com.scapegoat.infrastructure.bootstrap.dubbo.starter.QuickDubboAppBootstrap;
import com.scapegoat.infrastructure.bootstrap.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.charge.enums.HolidayTypeEnum;
import com.zoeeasy.cloud.charge.holiday.HolidayCalendarService;
import com.zoeeasy.cloud.charge.holiday.dto.request.HolidayCalendarDeleteRequestDto;
import com.zoeeasy.cloud.charge.holiday.dto.request.HolidayCalendarViewQueryRequestDto;
import com.zoeeasy.cloud.charge.holiday.dto.request.HolidayScheduleSaveRequestDto;
import com.zoeeasy.cloud.charge.holiday.dto.result.HolidayCalendarViewResultDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * @author AkeemSuper
 * @date 2018/10/29 0029
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboAppBootstrap.class)
public class HolidayCalendarServiceTest {

    @Autowired
    private HolidayCalendarService holidayCalendarService;

    @Test
    public void getHolidayCalendarList() {
        HolidayCalendarViewQueryRequestDto holidayCalendarViewQueryRequestDto = new HolidayCalendarViewQueryRequestDto();
        holidayCalendarViewQueryRequestDto.setQueryDate("2018-11");
        ListResultDto<HolidayCalendarViewResultDto> holidayCalendarList = holidayCalendarService.getHolidayCalendarList(holidayCalendarViewQueryRequestDto);
        assertTrue(holidayCalendarList.isSuccess());
    }

    @Test
    public void saveHolidaySchedule() {
        HolidayScheduleSaveRequestDto holidayScheduleSaveRequestDto = new HolidayScheduleSaveRequestDto();
        holidayScheduleSaveRequestDto.setHolidayDates("2018-10-01,2018-10-02");
        holidayScheduleSaveRequestDto.setHolidayName("国庆");
        holidayScheduleSaveRequestDto.setHolidayType(HolidayTypeEnum.LONG_VOCATION.getValue());
        ResultDto resultDto = holidayCalendarService.saveHolidaySchedule(holidayScheduleSaveRequestDto);
        assertTrue(resultDto.isSuccess());
    }

    @Test
    public void deleteHolidayCalendar() {
        HolidayCalendarDeleteRequestDto holidayCalendarDeleteRequestDto = new HolidayCalendarDeleteRequestDto();
        holidayCalendarDeleteRequestDto.setDeleteDate("2018-11-13");
        ResultDto resultDto = holidayCalendarService.deleteHolidayCalendar(holidayCalendarDeleteRequestDto);
        assertTrue(resultDto.isSuccess());
    }
}