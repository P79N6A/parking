package com.zoeeasy.cloud.ucc.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.ucc.domain.TenantEntity;
import org.springframework.stereotype.Repository;

/**
 * @author walkman
 * @date 2017-11-21
 */
@Repository("tenantMapper")
@SqlParser(filter = true)
public interface TenantMapper extends BaseMapper<TenantEntity> {

}