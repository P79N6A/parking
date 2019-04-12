package com.zoeeasy.cloud.charge.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.charge.domain.HolidayCalendarEntity;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/9/13 0013
 */
public interface HolidayCalendarCrudService extends CrudService<HolidayCalendarEntity> {

    /**
     * 通过日期查找节假日配置
     *
     * @param date
     * @return
     */
    HolidayCalendarEntity findByDate(String date);

    /**
     * 通过日期范围查找
     *
     * @param entityEntityWrapper
     * @return
     */
    List<HolidayCalendarEntity> selectDate(Wrapper<HolidayCalendarEntity> entityEntityWrapper);

    HolidayCalendarEntity selectDateAndTenantId(Wrapper<HolidayCalendarEntity> entityWrapper);
}
