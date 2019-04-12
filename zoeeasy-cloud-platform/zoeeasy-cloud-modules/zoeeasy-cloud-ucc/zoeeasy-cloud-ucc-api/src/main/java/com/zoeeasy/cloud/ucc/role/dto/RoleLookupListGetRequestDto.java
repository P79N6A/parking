package com.zoeeasy.cloud.ucc.role.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 角色列表请求参数表
 *
 * @author walkman
 */
@ApiModel(value = "RoleLookupListGetRequestDto", description = "角色列表请求参数表")
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleLookupListGetRequestDto extends SignedSessionRequestDto {

    /**
     * 角色代码
     */
    @ApiModelProperty("角色代码")
    private String code;

}
