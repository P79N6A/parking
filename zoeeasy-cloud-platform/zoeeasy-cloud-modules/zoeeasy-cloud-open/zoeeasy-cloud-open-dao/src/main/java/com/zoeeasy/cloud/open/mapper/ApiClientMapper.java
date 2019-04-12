package com.zoeeasy.cloud.open.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.open.domain.ApiClientEntity;
import org.springframework.stereotype.Repository;


/**
 * @author walkman
 * @since 2017-11-21
 */
@Repository("apiClientMapper")
public interface ApiClientMapper extends BaseMapper<ApiClientEntity> {

}