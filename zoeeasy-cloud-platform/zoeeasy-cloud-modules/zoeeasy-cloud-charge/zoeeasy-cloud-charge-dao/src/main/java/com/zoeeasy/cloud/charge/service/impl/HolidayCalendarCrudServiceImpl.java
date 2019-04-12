package com.zoeeasy.cloud.charge.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.charge.domain.HolidayCalendarEntity;
import com.zoeeasy.cloud.charge.mapper.HolidayCalendarMapper;
import com.zoeeasy.cloud.charge.service.HolidayCalendarCrudService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/9/13 0013
 */
@Service("holidayCalendarCrudService")
public class HolidayCalendarCrudServiceImpl extends CrudServiceImpl<HolidayCalendarMapper, HolidayCalendarEntity> implements HolidayCalendarCrudService {

    @Override
    public HolidayCalendarEntity findByDate(String date) {
        HolidayCalendarEntity holidayCalendarEntity = new HolidayCalendarEntity();
        holidayCalendarEntity.setDate(date);
        return baseMapper.selectOne(holidayCalendarEntity);
    }

    /**
     * 通过日期范围查找
     *
     * @param entityEntityWrapper
     * @return
     */
    @Override
    public List<HolidayCalendarEntity> selectDate(Wrapper<HolidayCalendarEntity> entityEntityWrapper) {
        return baseMapper.selectByDate(entityEntityWrapper);
    }

    @Override
    public HolidayCalendarEntity selectDateAndTenantId(Wrapper<HolidayCalendarEntity> entityWrapper) {
        List<HolidayCalendarEntity> calendarEntities = baseMapper.selectByDate(entityWrapper);
        if (CollectionUtils.isNotEmpty(calendarEntities)) {
            return calendarEntities.get(0);
        }
        return null;
    }
}
