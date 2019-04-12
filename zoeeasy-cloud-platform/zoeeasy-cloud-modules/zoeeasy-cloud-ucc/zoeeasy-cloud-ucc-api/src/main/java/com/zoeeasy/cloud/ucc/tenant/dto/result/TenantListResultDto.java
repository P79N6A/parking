package com.zoeeasy.cloud.ucc.tenant.dto.result;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 租户列表结果视图
 *
 * @author walkman
 * @since 2018-08-28
 */
@ApiModel(value = "TenantListResultDto", description = "租户列表结果视图")
@Data
@EqualsAndHashCode(callSuper = true)
public class TenantListResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 租户编码
     */
    @ApiModelProperty("租户编码")
    private String code;

    /**
     * 租户名称
     */
    @ApiModelProperty("租户名称")
    private String name;

    /**
     * 商户类型
     */
    @ApiModelProperty("商户类型")
    private Integer type;

    /**
     * 商户状态
     */
    @ApiModelProperty("商户状态")
    private Integer status;

    /**
     * 管理员账号
     */
    @ApiModelProperty("管理员账号")
    private String adminAccount;

}
