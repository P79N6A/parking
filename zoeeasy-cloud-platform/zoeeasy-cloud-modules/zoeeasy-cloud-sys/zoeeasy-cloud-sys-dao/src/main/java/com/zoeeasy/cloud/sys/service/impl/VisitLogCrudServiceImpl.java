package com.zoeeasy.cloud.sys.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.sys.domain.VisitLogEntity;
import com.zoeeasy.cloud.sys.mapper.VisitLogMapper;
import com.zoeeasy.cloud.sys.service.VisitLogCrudService;
import org.springframework.stereotype.Service;

/**
 * 用户登录日志表(VisitLog)表服务实现类
 *
 * @author AkeemSuper
 * @since 2019-02-20 10:37:33
 */
@Service("visitLogCrudService")
public class VisitLogCrudServiceImpl extends CrudServiceImpl<VisitLogMapper, VisitLogEntity> implements VisitLogCrudService {
}