package com.zhuyitech.parking.ucc.dto.result.user;

import com.scapegoat.infrastructure.core.dto.result.FullAuditedEntityDto;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户视图模型
 *
 * @author walkman
 */
@ApiModel(value = "UserResultDto", description = "用户视图模型")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserResultDto extends FullAuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * uuid
     */
    @ApiModelProperty("uuid")
    private String uuid;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String nickname;

    /**
     * 盐
     */
    @ApiModelProperty("盐")
    private String salt;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String portrait;

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String phoneNumber;

    /**
     * 手机号是否确认
     */
    @ApiModelProperty("手机号是否确认")
    private Boolean phoneNumberConfirmed;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String emailAddress;

    /**
     * 邮箱是否确认
     */
    @ApiModelProperty("邮箱是否确认")
    private Boolean emailAddressConfirmed;

    /**
     * 失败登录次数
     */
    @ApiModelProperty("失败登录次数")
    private Integer accessAttemptCount;

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
    protected Date lastLoginTime;

    /**
     * 别名(用于推送)
     */
    @ApiModelProperty(value = "别名(用于推送)")
    private String alias;

}
