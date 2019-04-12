package com.zoeeasy.cloud.ucc.tenant.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 租户管理员账号校验结果
 */
@ApiModel(value = "TenantAccountCheckResultDto", description = "租户管理员账号校验结果")
@Data
@EqualsAndHashCode(callSuper = true)
public class TenantAccountCheckResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("名称是否可用")
    private Boolean available;

    @ApiModelProperty("提示信息")
    private String message;
}