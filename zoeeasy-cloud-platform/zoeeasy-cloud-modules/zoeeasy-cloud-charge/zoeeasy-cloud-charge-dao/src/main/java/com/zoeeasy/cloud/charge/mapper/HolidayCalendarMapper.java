package com.zoeeasy.cloud.charge.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zoeeasy.cloud.charge.domain.HolidayCalendarEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: AkeemSuper
 */
@Repository
public interface HolidayCalendarMapper extends BaseMapper<HolidayCalendarEntity> {

    /**
     * 通过日期范围查找
     *
     * @param entityEntityWrapper
     * @return
     */
    @SqlParser(filter = true)
    List<HolidayCalendarEntity> selectByDate(@Param("ew") Wrapper<HolidayCalendarEntity> entityEntityWrapper);
}
