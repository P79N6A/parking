package com.zoeeasy.cloud.ucc.user.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 用户视图模型
 *
 * @author walkman
 */
@ApiModel(value = "UserListResultDto", description = "用户视图模型")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserListResultDto extends AuditedEntityDto<Long> {

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
     * 是否管理员用户
     */
    @ApiModelProperty("是否管理员用户")
    private Boolean adminUser;

    /**
     * 最后登录时间
     */
    @ApiModelProperty("最后登录时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date lastLoginTime;

}
