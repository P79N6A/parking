package com.zoeeasy.cloud.sys.service.impl;


import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.sys.domain.AuditLogEntity;
import com.zoeeasy.cloud.sys.mapper.AuditLogMapper;
import com.zoeeasy.cloud.sys.service.AuditLogCrudService;
import org.springframework.stereotype.Service;

/**
 * 操作日志Crud服务
 *
 * @author walkman
 */
@Service("auditLogCrudService")
public class AuditLogCrudServiceImpl extends CrudServiceImpl<AuditLogMapper, AuditLogEntity> implements AuditLogCrudService {

}
