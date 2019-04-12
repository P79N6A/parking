package com.zoeeasy.cloud.charge.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zoeeasy.cloud.charge.domain.HolidayScheduleEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: AkeemSuper
 */
@Repository
public interface HolidayScheduleMapper extends BaseMapper<HolidayScheduleEntity> {

    @SqlParser(filter = true)
    List<HolidayScheduleEntity> findByEntityWrapper(@Param("ew") EntityWrapper<HolidayScheduleEntity> entityWrapper);

    @SqlParser(filter = true)
    HolidayScheduleEntity findOneByEntityWrapper(@Param("ew") EntityWrapper<HolidayScheduleEntity> entityWrapper);
}
