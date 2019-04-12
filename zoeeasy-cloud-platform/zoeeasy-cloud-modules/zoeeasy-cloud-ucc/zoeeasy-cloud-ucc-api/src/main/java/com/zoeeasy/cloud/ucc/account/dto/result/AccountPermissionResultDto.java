package com.zoeeasy.cloud.ucc.account.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 账户角色视图模型
 *
 * @author walkman
 */
@ApiModel(value = "AccountRoleResultDto", description = "账户角色视图模型")
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountPermissionResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * code
     */
    @ApiModelProperty("code")
    private String code;

    /**
     * 租户编号
     */
    @ApiModelProperty("name")
    private String name;

}
