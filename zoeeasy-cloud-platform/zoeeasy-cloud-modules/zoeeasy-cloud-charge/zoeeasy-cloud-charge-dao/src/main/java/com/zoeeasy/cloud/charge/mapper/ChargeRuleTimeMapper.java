package com.zoeeasy.cloud.charge.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.charge.domain.ChargeRuleTimeEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: AkeemSuper
 */
@Repository
public interface ChargeRuleTimeMapper extends BaseMapper<ChargeRuleTimeEntity> {

    /**
     * 根据规则id查找
     *
     * @param id
     * @return
     */
    @SqlParser(filter = true)
    List<ChargeRuleTimeEntity> findRuleId(@Param("ruleId") Long id, @Param("tenantId") Long tenantId);
}
