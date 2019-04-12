package com.zoeeasy.cloud.charge.holiday;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.zoeeasy.cloud.charge.holiday.dto.request.HolidayTypeGetRequestDto;

/**
 * @author AkeemSuper
 * @date 2018/9/14 0014
 */
public interface HolidayScheduleService {

    /**
     * 获取假期类型下拉菜单
     *
     * @return
     */
    ListResultDto<ComboboxItemDto> getHolidayTypeList(HolidayTypeGetRequestDto requestDto);

    /**
     * 保存假期类型时下拉列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ComboboxItemDto> saveHolidayTypeList(HolidayTypeGetRequestDto requestDto);
}
