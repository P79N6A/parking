package com.zoeeasy.cloud.charge.service.impl;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.zoeeasy.cloud.charge.enums.HolidayTypeEnum;
import com.zoeeasy.cloud.charge.holiday.HolidayScheduleService;
import com.zoeeasy.cloud.charge.holiday.dto.request.HolidayTypeGetRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 节假日相关服务
 *
 * @author AkeemSuper
 * @date 2018/9/14 0014
 */
@Service(value = "holidayScheduleService")
@Slf4j
public class HolidayScheduleServiceImpl implements HolidayScheduleService {

    /**
     * 获取假期类型下拉菜单
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> getHolidayTypeList(HolidayTypeGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto(String.valueOf(HolidayTypeEnum.WORK_DAY.getValue()), HolidayTypeEnum.WORK_DAY.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(HolidayTypeEnum.WEEKEND.getValue()), HolidayTypeEnum.WEEKEND.getComment(), true));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(HolidayTypeEnum.MINOR_VOCATION.getValue()), HolidayTypeEnum.MINOR_VOCATION.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(HolidayTypeEnum.LONG_VOCATION.getValue()), HolidayTypeEnum.LONG_VOCATION.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(HolidayTypeEnum.ALL.getValue()), HolidayTypeEnum.ALL.getComment(), false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取假期类型下拉菜单失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }

    /**
     * 保存假期类型时下拉列表
     *
     * @param requestDto
     * @return
     */
    @Override
    public ListResultDto<ComboboxItemDto> saveHolidayTypeList(HolidayTypeGetRequestDto requestDto) {
        ListResultDto<ComboboxItemDto> listResultDto = new ListResultDto<>();
        try {
            List<ComboboxItemDto> itemDtoList = new ArrayList<>();
            itemDtoList.add(new ComboboxItemDto(String.valueOf(HolidayTypeEnum.WEEKEND.getValue()), HolidayTypeEnum.WEEKEND.getComment(), true));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(HolidayTypeEnum.MINOR_VOCATION.getValue()), HolidayTypeEnum.MINOR_VOCATION.getComment(), false));
            itemDtoList.add(new ComboboxItemDto(String.valueOf(HolidayTypeEnum.LONG_VOCATION.getValue()), HolidayTypeEnum.LONG_VOCATION.getComment(), false));
            listResultDto.setItems(itemDtoList);
            listResultDto.success();
        } catch (Exception e) {
            log.error("获取假期类型下拉菜单失败" + e.getMessage());
            listResultDto.failed();
        }
        return listResultDto;
    }
}
