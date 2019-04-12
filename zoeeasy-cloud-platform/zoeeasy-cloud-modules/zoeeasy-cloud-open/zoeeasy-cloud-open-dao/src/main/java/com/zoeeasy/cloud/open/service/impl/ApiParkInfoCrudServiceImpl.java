package com.zoeeasy.cloud.open.service.impl;


import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.open.domain.ApiParkinfoEntity;
import com.zoeeasy.cloud.open.mapper.ApiParkInfoMapper;
import com.zoeeasy.cloud.open.service.ApiParkInfoCrudService;
import org.springframework.stereotype.Service;


/**
 * @author walkman
 * @since 2018-08-20
 */
@Service("apiParkInfoCrudService")
public class ApiParkInfoCrudServiceImpl extends CrudServiceImpl<ApiParkInfoMapper, ApiParkinfoEntity> implements ApiParkInfoCrudService {


}

