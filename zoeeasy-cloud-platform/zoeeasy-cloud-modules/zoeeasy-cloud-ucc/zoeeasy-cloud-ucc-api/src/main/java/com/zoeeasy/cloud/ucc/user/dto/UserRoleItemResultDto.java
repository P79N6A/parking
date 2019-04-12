package com.zoeeasy.cloud.ucc.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户角色项视图模型
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserRoleItemResultDto", description = "用户角色项视图模型")
public class UserRoleItemResultDto extends BaseDto {

    /**
     * 角色ID
     */
    @ApiModelProperty("角色ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;

    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    private String roleName;
}
