package com.zoeeasy.cloud.charge.holiday;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.holiday.dto.request.HolidayCalendarDeleteRequestDto;
import com.zoeeasy.cloud.charge.holiday.dto.request.HolidayCalendarViewQueryRequestDto;
import com.zoeeasy.cloud.charge.holiday.dto.request.HolidayScheduleSaveRequestDto;
import com.zoeeasy.cloud.charge.holiday.dto.result.HolidayCalendarViewResultDto;

/**
 * @author AkeemSuper
 * @date 2018/9/14 0014
 */
public interface HolidayCalendarService {

    /**
     * 获取假期日历列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<HolidayCalendarViewResultDto> getHolidayCalendarList(HolidayCalendarViewQueryRequestDto requestDto);

    /**
     * 保存节假日配置
     *
     * @param requestDto
     * @return
     */
    ResultDto saveHolidaySchedule(HolidayScheduleSaveRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 删除节假日配置
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteHolidayCalendar(HolidayCalendarDeleteRequestDto requestDto) throws ValidationException, BusinessException;

}
