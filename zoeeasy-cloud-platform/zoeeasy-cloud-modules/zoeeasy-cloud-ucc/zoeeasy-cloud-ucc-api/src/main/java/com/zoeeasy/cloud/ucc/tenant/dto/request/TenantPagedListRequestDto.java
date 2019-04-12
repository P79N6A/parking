package com.zoeeasy.cloud.ucc.tenant.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 租户列表请求参数
 *
 * @author walkman
 */
@ApiModel(value = "TenantPagedListRequestDto", description = "商户列表请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class TenantPagedListRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商户编号")
    private String code;

    @ApiModelProperty(value = "商户名称")
    private String name;

    @ApiModelProperty(value = "商户类型")
    private Integer type;

    @ApiModelProperty(value = "商户状态")
    private Integer status;

    @ApiModelProperty(value = "管理员账号")
    private String adminAccount;


}
