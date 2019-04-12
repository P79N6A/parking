package com.zoeeasy.cloud.charge.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.zoeeasy.cloud.charge.domain.ParkingChargeRuleEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: AkeemSuper
 */
@Repository
public interface ParkingChargeRuleMapper extends BaseMapper<ParkingChargeRuleEntity> {

    /**
     * 条件查询停车场收费规则
     *
     * @param entityWrapper
     * @return
     */
    @SqlParser(filter = true)
    List<ParkingChargeRuleEntity> selectChargeRule(@Param("ew") Wrapper<ParkingChargeRuleEntity> entityWrapper);

    @SqlParser(filter = true)
    Integer selectChargeTotal(@Param("parkingId") Long parkingId);

    @SqlParser(filter = true)
    List<ParkingChargeRuleEntity> selectChargeRulePaged(Pagination page, @Param("ew") Wrapper<ParkingChargeRuleEntity> ew);

    @SqlParser(filter = true)
    boolean deleteParkingChargeRuleNonTenant(@Param("ew") Wrapper<ParkingChargeRuleEntity> ew);
}
