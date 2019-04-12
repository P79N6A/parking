package com.zoeeasy.cloud.ucc.user.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.ucc.user.dto.UserRoleItemResultDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 用户详情视图模型
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserDetailResultDto", description = "用户详情视图模型")
public class UserDetailResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * uuid
     */
    @ApiModelProperty("uuid")
    private String uuid;

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
     * 用户姓名
     */
    @ApiModelProperty("用户姓名")
    private String realName;

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String phoneNumber;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String emailAddress;

    /**
     * 用户状态
     */
    @ApiModelProperty("用户状态")
    private Integer status;

    /**
     * 用户类型
     */
    @ApiModelProperty("用户类型")
    private Integer userType;

    /**
     * 最后登录时间
     */
    @ApiModelProperty("最后登录时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date lastLoginTime;

    /**
     * 部门ID
     */
    @ApiModelProperty("部门ID")
    private List<Long> organizationId;

    /**
     * 部门名称
     */
    @ApiModelProperty("部门名称")
    private String organizationName;

    /**
     * 角色列表
     */
    @ApiModelProperty("角色列表")
    private List<UserRoleItemResultDto> roles;

}