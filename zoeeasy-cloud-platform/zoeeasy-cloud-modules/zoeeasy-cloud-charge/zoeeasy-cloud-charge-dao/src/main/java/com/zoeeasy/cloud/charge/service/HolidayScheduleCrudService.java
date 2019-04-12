package com.zoeeasy.cloud.charge.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.charge.domain.HolidayScheduleEntity;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/9/13 0013
 */
public interface HolidayScheduleCrudService extends CrudService<HolidayScheduleEntity> {

    List<HolidayScheduleEntity> findByIdAndHolidayType(Integer holidayType, Long tenantId, List<Long> ids);

    HolidayScheduleEntity findById(Long holidayId);
}
