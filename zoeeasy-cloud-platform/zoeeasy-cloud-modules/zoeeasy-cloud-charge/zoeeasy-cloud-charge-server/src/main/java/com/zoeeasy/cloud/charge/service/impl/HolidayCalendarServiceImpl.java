package com.zoeeasy.cloud.charge.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.charge.chg.validator.HolidayCalendarDeleteRequestDtoValidator;
import com.zoeeasy.cloud.charge.domain.HolidayCalendarEntity;
import com.zoeeasy.cloud.charge.domain.HolidayScheduleEntity;
import com.zoeeasy.cloud.charge.enums.ChargeResultEnum;
import com.zoeeasy.cloud.charge.enums.HolidayTypeEnum;
import com.zoeeasy.cloud.charge.holiday.HolidayCalendarService;
import com.zoeeasy.cloud.charge.holiday.dto.request.HolidayCalendarDeleteRequestDto;
import com.zoeeasy.cloud.charge.holiday.dto.request.HolidayCalendarViewQueryRequestDto;
import com.zoeeasy.cloud.charge.holiday.dto.request.HolidayScheduleSaveRequestDto;
import com.zoeeasy.cloud.charge.holiday.dto.result.HolidayCalendarViewResultDto;
import com.zoeeasy.cloud.charge.holiday.validator.HolidayScheduleSaveRequestDtoValidator;
import com.zoeeasy.cloud.charge.service.HolidayCalendarCrudService;
import com.zoeeasy.cloud.charge.service.HolidayScheduleCrudService;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.core.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 日历相关服务
 *
 * @author AkeemSuper
 * @date 2018/9/14 0014
 */
@Service(value = "holidayCalendarService")
@Slf4j
public class HolidayCalendarServiceImpl implements HolidayCalendarService {

    @Autowired
    private HolidayScheduleCrudService holidayScheduleCrudService;

    @Autowired
    private HolidayCalendarCrudService holidayCalendarCrudService;

    /**
     * 获取假期日历列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<HolidayCalendarViewResultDto> getHolidayCalendarList(HolidayCalendarViewQueryRequestDto requestDto) {
        ListResultDto<HolidayCalendarViewResultDto> resultDto = new ListResultDto<>();
        try {
            if (!StringUtils.isEmpty(requestDto.getQueryDate())) {
                Boolean date = Utils.isDate(requestDto.getQueryDate(), Const.FORMAT_DATE_MONTH);
                if (!date) {
                    return resultDto.makeResult(ChargeResultEnum.HOLIDAY_DATE_FOMART_FAIL.getValue(), ChargeResultEnum.HOLIDAY_DATE_FOMART_FAIL.getComment());
                }
            } else {
                String defaultDate = DateUtils.formatDate(DateUtils.now(), Const.FORMAT_DATE_MONTH);
                requestDto.setQueryDate(defaultDate);
            }
            Wrapper<HolidayCalendarEntity> entityWrapper = new EntityWrapper<>();
            entityWrapper.like("date", requestDto.getQueryDate());
            List<HolidayCalendarEntity> holidayCalendarEntities = holidayCalendarCrudService.selectList(entityWrapper);
            if (!holidayCalendarEntities.isEmpty()) {
                ArrayList<HolidayCalendarViewResultDto> holidayCalendarViewResultDtos = new ArrayList<>();
                for (HolidayCalendarEntity holidayCalendarEntity : holidayCalendarEntities) {
                    HolidayScheduleEntity holidayScheduleEntity = holidayScheduleCrudService.selectById(holidayCalendarEntity.getHolidayId());
                    if (null == holidayScheduleEntity) {
                        return resultDto.makeResult(ChargeResultEnum.HOLIDAY_SCHEDULE_EMPTY.getValue(), ChargeResultEnum.HOLIDAY_SCHEDULE_EMPTY.getComment());
                    }
                    HolidayCalendarViewResultDto holidayCalendarViewResultDto = new HolidayCalendarViewResultDto();
                    holidayCalendarViewResultDto.setDate(holidayCalendarEntity.getDate());
                    holidayCalendarViewResultDto.setHolidayName(holidayScheduleEntity.getHolidayName());
                    holidayCalendarViewResultDto.setHolidayType(holidayScheduleEntity.getHolidayType());
                    holidayCalendarViewResultDtos.add(holidayCalendarViewResultDto);
                }
                resultDto.setItems(holidayCalendarViewResultDtos);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("获取假期日历列表失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 保存节假日配置
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto saveHolidaySchedule(@FluentValid({HolidayScheduleSaveRequestDtoValidator.class}) HolidayScheduleSaveRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            String[] split = requestDto.getHolidayDates().split(",");
            HolidayScheduleEntity holidayScheduleEntity = new HolidayScheduleEntity();
            holidayScheduleEntity.setHolidayName(requestDto.getHolidayName());
            holidayScheduleEntity.setHolidayType(requestDto.getHolidayType());
            holidayScheduleCrudService.insert(holidayScheduleEntity);
            for (String date : split) {
                HolidayCalendarEntity holidayCalendarEntity = new HolidayCalendarEntity();
                holidayCalendarEntity.setDate(date);
                HolidayCalendarEntity holiday = holidayCalendarCrudService.findByDate(date);
                if (holiday != null) {
                    return resultDto.makeResult(ChargeResultEnum.HOLIDAY_CALENDAR_EXIST.getValue(), ChargeResultEnum.HOLIDAY_CALENDAR_EXIST.getComment());
                }
                holidayCalendarEntity.setHolidayId(holidayScheduleEntity.getId());
                int week = LocalDate.parse(date).getDayOfWeek().getValue();
                if (week > 5) {
                    holidayCalendarEntity.setWorkDay(Boolean.FALSE);
                } else {
                    if (requestDto.getHolidayType().equals(HolidayTypeEnum.WEEKEND.getValue())) {
                        return resultDto.makeResult(ChargeResultEnum.HOLIDAY_DATE_NOT_WEEKDAY.getValue(), ChargeResultEnum.HOLIDAY_DATE_NOT_WEEKDAY.getComment());
                    }
                    holidayCalendarEntity.setWorkDay(Boolean.TRUE);
                }
                holidayCalendarCrudService.insert(holidayCalendarEntity);

            }
            resultDto.success();
        } catch (Exception e) {
            log.error("保存节假日配置失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 删除节假日配置
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto deleteHolidayCalendar(@FluentValid({HolidayCalendarDeleteRequestDtoValidator.class}) HolidayCalendarDeleteRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            Date parseDate = DateUtils.parseDate(DateUtils.getDate(), Const.FORMAT_DATE);
            HolidayCalendarEntity holidayCalendarEntity = holidayCalendarCrudService.findByDate(requestDto.getDeleteDate());
            if (holidayCalendarEntity == null) {
                return resultDto.makeResult(ChargeResultEnum.HOLIDAY_CALENDAR_EMPTY.getValue(), ChargeResultEnum.HOLIDAY_CALENDAR_EMPTY.getComment());
            }
            EntityWrapper<HolidayCalendarEntity> entityEntityWrapper = new EntityWrapper<>();
            entityEntityWrapper.eq("holidayId", holidayCalendarEntity.getHolidayId());
            List<HolidayCalendarEntity> holidayCalendarEntities = holidayCalendarCrudService.selectList(entityEntityWrapper);
            if (!holidayCalendarEntities.isEmpty()) {
                for (HolidayCalendarEntity calendarEntity : holidayCalendarEntities) {
                    String date = calendarEntity.getDate();
                    Date holidayDate = DateUtils.parseDate(date, Const.FORMAT_DATE);
                    if (holidayDate.before(parseDate)) {
                        return resultDto.makeResult(ChargeResultEnum.HOLIDAY_CALENDAR_DELETE_DATE_ERROR.getValue(), ChargeResultEnum.HOLIDAY_CALENDAR_DELETE_DATE_ERROR.getComment());
                    }
                }
                holidayScheduleCrudService.deleteById(holidayCalendarEntity.getHolidayId());
                holidayCalendarCrudService.delete(entityEntityWrapper);
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("删除节假日配置失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

}
