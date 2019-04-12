package com.zoeeasy.cloud.ucc.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.ucc.domain.OrganizationEntity;
import org.springframework.stereotype.Repository;

/**
 * @author walkman
 * @date 2017-11-21
 */
@Repository("organizationMapper")
public interface OrganizationMapper extends BaseMapper<OrganizationEntity> {

}