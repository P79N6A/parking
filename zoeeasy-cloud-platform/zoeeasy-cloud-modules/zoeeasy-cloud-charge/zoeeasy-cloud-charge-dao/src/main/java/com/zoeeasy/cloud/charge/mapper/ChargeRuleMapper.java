package com.zoeeasy.cloud.charge.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.charge.domain.ChargeRuleEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: AkeemSuper
 */
@Repository
public interface ChargeRuleMapper extends BaseMapper<ChargeRuleEntity> {

    /**
     * 根据id数组批量查询
     *
     * @param ids
     * @return
     */
    @SqlParser(filter = true)
    List<ChargeRuleEntity> findByIds(@Param("list") List<Long> ids);

    @SqlParser(filter = true)
    ChargeRuleEntity selectByRuleId(@Param("ruleId") Long ruleId, @Param("tenantId") Long tenantId);

    @SqlParser(filter = true)
    ChargeRuleEntity selectById(@Param("ruleId") Long ruleId);
}
