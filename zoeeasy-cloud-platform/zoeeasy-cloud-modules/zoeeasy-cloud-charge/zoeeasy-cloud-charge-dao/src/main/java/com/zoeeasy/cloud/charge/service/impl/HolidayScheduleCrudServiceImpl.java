package com.zoeeasy.cloud.charge.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.charge.domain.HolidayScheduleEntity;
import com.zoeeasy.cloud.charge.mapper.HolidayScheduleMapper;
import com.zoeeasy.cloud.charge.service.HolidayScheduleCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/9/13 0013
 */
@Service("holidayScheduleCrudService")
public class HolidayScheduleCrudServiceImpl extends CrudServiceImpl<HolidayScheduleMapper, HolidayScheduleEntity> implements HolidayScheduleCrudService {

    @Override
    public List<HolidayScheduleEntity> findByIdAndHolidayType(Integer holidayType, Long tenantId, List<Long> ids) {
        EntityWrapper<HolidayScheduleEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("tenantId", tenantId);
        entityWrapper.eq("holidayType", holidayType);
        entityWrapper.in("id", ids);
        return baseMapper.findByEntityWrapper(entityWrapper);
    }

    @Override
    public HolidayScheduleEntity findById(Long holidayId) {
        EntityWrapper<HolidayScheduleEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("id", holidayId);
        return baseMapper.findOneByEntityWrapper(entityWrapper);
    }
}
