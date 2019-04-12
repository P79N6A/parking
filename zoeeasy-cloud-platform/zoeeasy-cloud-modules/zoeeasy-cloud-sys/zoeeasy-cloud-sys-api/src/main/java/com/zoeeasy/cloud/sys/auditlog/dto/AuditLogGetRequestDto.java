package com.zoeeasy.cloud.sys.auditlog.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;


/**
 * 获取操作日志请求参数
 *
 * @author walkman
 */
@ApiModel(value = "AuditLogGetRequestDto", description = "获取操作日志请求参数")
public class AuditLogGetRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;
}