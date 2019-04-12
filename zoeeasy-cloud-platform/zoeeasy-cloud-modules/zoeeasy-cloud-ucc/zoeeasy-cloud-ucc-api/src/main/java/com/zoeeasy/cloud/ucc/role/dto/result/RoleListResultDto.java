package com.zoeeasy.cloud.ucc.role.dto.result;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色视图模型
 *
 * @author walkman
 */
@ApiModel(value = "RoleListResultDto", description = "角色视图模型")
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleListResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色代码
     */
    @ApiModelProperty("角色代码")
    private String code;

    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    private String name;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;

    /**
     *
     */
    @ApiModelProperty("是否静态角色")
    private Boolean staticRole;

    /**
     *
     */
    @ApiModelProperty("是否默认角色")
    private Boolean defaultRole;

    /**
     *
     */
    @ApiModelProperty("是否是管理员角色")
    private Boolean adminRole;

}