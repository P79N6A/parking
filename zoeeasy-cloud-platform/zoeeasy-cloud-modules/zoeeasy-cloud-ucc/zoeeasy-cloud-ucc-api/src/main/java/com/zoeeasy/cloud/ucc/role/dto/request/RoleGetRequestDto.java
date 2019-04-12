package com.zoeeasy.cloud.ucc.role.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 获取角色请求参数
 *
 * @author walkman
 */
@ApiModel(value = "RoleGetRequestDto", description = "获取角色请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleGetRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色代码
     */
    @ApiModelProperty(name = "角色代码")
    private String code;

}
