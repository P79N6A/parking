package com.zoeeasy.cloud.ucc.tenant.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 租户列表请求参数
 */
@ApiModel(value = "TenantListRequestDto", description = "租户列表请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class TenantListRequestDto extends SignedSessionRequestDto {

    /**
     * 租户名
     */
    @ApiModelProperty("name")
    private String name;

}
