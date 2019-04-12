package com.zoeeasy.cloud.open.service.impl;


import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.open.domain.ApiClientEntity;
import com.zoeeasy.cloud.open.mapper.ApiClientMapper;
import com.zoeeasy.cloud.open.service.ApiClientCrudService;
import org.springframework.stereotype.Service;


/**
 * @author walkman
 * @since 2018-08-20
 */
@Service("apiClientCrudService")
public class ApiClientCrudServiceImpl extends CrudServiceImpl<ApiClientMapper, ApiClientEntity> implements ApiClientCrudService {


}
