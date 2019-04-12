package com.zoeeasy.cloud.inspect.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.inspect.domain.InspectRecordEntity;
import com.zoeeasy.cloud.inspect.mapper.InspectRecordMapper;
import com.zoeeasy.cloud.inspect.service.InspectRecordCrudService;
import org.springframework.stereotype.Service;

/**
 * @author AkeemSuper
 * @date 2018/9/20 0020
 */
@Service("inspectRecordCrudService")
public class InspectRecordCrudServiceImpl extends CrudServiceImpl<InspectRecordMapper, InspectRecordEntity> implements InspectRecordCrudService {

}
