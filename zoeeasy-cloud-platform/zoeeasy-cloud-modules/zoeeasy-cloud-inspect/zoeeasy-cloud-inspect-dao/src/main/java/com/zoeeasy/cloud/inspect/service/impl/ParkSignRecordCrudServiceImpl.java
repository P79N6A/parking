package com.zoeeasy.cloud.inspect.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.inspect.domain.ParkSignRecordEntity;
import com.zoeeasy.cloud.inspect.mapper.ParkSignRecordMapper;
import com.zoeeasy.cloud.inspect.service.ParkSignRecordCrudService;
import org.springframework.stereotype.Service;

/**
 * @author AkeemSuper
 * @date 2018/9/20 0020
 */
@Service("parkSignRecordCrudService")
public class ParkSignRecordCrudServiceImpl extends CrudServiceImpl<ParkSignRecordMapper, ParkSignRecordEntity> implements ParkSignRecordCrudService {
}
