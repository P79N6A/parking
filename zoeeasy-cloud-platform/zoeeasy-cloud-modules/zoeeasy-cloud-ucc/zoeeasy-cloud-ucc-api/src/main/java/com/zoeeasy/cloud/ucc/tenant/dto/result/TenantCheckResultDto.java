package com.zoeeasy.cloud.ucc.tenant.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 租户名称校验结果
 */
@ApiModel(value = "TenantCheckResultDto", description = "租户结果视图")
@Data
@EqualsAndHashCode(callSuper = true)
public class TenantCheckResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("名称是否可用")
    private Boolean available;

    @ApiModelProperty("提示信息")
    private String message;
}
