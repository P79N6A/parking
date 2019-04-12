package com.zoeeasy.cloud.sys.auditlog.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @date: 2019/2/22.
 * @author：zm
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AuditLogTypeGetRequestDto", description = "操作类型获取请求参数")
public class AuditLogTypeGetRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;
}
