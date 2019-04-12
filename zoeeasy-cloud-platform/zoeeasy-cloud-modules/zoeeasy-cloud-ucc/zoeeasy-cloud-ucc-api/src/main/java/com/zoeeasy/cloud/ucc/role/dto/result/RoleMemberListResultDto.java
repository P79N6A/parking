package com.zoeeasy.cloud.ucc.role.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 角色成员用户视图
 *
 * @author walkman
 */
@ApiModel(value = "角色成员用户视图", description = "获取角色可授权菜单树请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleMemberListResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 账号
     */
    @ApiModelProperty("account")
    private String account;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 手机号
     */
    @ApiModelProperty("用户名")
    private String phoneNumber;

    /**
     * 用户姓名
     */
    @ApiModelProperty("用户姓名")
    private String realName;

}
