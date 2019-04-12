package com.zoeeasy.cloud.core.support;

import com.zoeeasy.cloud.core.model.AuditLogModel;

/**
 *
 */
public interface AuditLogRepository {

    /**
     * 保存日志
     *
     * @param auditLog
     */
    void saveAuditLog(AuditLogModel auditLog);
}
