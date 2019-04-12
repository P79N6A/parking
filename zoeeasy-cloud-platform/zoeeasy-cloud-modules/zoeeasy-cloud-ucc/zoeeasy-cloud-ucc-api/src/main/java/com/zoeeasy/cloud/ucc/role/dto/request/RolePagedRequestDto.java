package com.zoeeasy.cloud.ucc.role.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询角色请求参数
 *
 * @author walkman
 */
@ApiModel(value = "RolePagedRequestDto", description = "分页查询角色请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class RolePagedRequestDto extends SignedSessionPagedRequestDto {

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

}
