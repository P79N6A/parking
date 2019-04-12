package com.zoeeasy.cloud.platform.suppport;

import com.zoeeasy.cloud.core.model.AuditLogModel;
import com.zoeeasy.cloud.core.support.AuditLogRepository;
import com.zoeeasy.cloud.sys.auditlog.AuditLogService;
import com.zoeeasy.cloud.sys.auditlog.dto.AuditLogAddRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 操作审计日志存储
 *
 * @author walkman
 */
@Component
public class AuditLogRepositoryImpl implements AuditLogRepository {

    @Autowired
    private AuditLogService auditLogService;

    @Override
    public void saveAuditLog(AuditLogModel auditLog) {

        AuditLogAddRequestDto auditLogAddRequestDto = new AuditLogAddRequestDto();
        auditLogAddRequestDto.setTenantId(auditLog.getTenantId());
        auditLogAddRequestDto.setUserId(auditLog.getUserId());
        auditLogAddRequestDto.setAccount(auditLog.getAccount());
        auditLogAddRequestDto.setType(auditLog.getType());
        auditLogAddRequestDto.setLogType(auditLog.getLogType());
        auditLogAddRequestDto.setOperatorType(auditLog.getOperatorType());
        auditLogAddRequestDto.setMethod(auditLog.getMethod());
        auditLogAddRequestDto.setRemoteAddress(auditLog.getRemoteAddress());
        auditLogAddRequestDto.setUserAgent(auditLog.getUserAgent());
        auditLogAddRequestDto.setRequestUrl(auditLog.getRequestUrl());
        auditLogAddRequestDto.setTitle(auditLog.getTitle());
        auditLogAddRequestDto.setContent(auditLog.getContent());
        auditLogAddRequestDto.setParams(auditLog.getParams());
        auditLogAddRequestDto.setException(auditLog.getException());
        auditLogAddRequestDto.setStatus(auditLog.getStatus());
        auditLogService.addAuditLog(auditLogAddRequestDto);
    }

}
