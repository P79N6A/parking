package com.zoeeasy.cloud.sys.auditlog.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;


/**
 * 删除操作日志请求参数
 *
 * @author walkman
 */
@ApiModel(description = "删除操作日志请求参数")
public class AuditLogDeleteRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

}