package com.zoeeasy.cloud.charge.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zoeeasy.cloud.charge.domain.ParkingAppointmentRuleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zwq
 * @since 2018-03-30
 */
public interface ParkingAppointmentRuleMapper extends BaseMapper<ParkingAppointmentRuleEntity> {

    /**
     * 查询停车场预约规则
     *
     * @param wrapper
     * @return
     */
    @SqlParser(filter = true)
    ParkingAppointmentRuleEntity selectParkingAppointRule(@Param("ew") Wrapper<ParkingAppointmentRuleEntity> wrapper);

    /**
     * 查询停车场预约规则列表
     *
     * @param wrapper
     * @return
     */
    @SqlParser(filter = true)
    List<ParkingAppointmentRuleEntity> selectParkingAppointRuleList(@Param("ew") Wrapper<ParkingAppointmentRuleEntity> wrapper);

    @SqlParser(filter = true)
    Integer selectParkingAppointRuleCount(@Param("ruleId") Long ruleId);

    @SqlParser(filter = true)
    boolean deleteParkingAppointRuleNonTenant(@Param("parkingId") Long parkingId);

}
