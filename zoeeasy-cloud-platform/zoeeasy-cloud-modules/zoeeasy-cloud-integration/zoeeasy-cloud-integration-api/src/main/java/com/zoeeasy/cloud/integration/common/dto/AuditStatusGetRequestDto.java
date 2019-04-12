package com.zoeeasy.cloud.integration.common.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 审核状态选项请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AuditStatusGetRequestDto", description = "审核状态选项请求参数")
public class AuditStatusGetRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;
}