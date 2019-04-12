package com.zoeeasy.cloud.ucc.account.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.core.multitenancy.TenancyHostSide;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 账号视图模型
 *
 * @author walkman
 */
@ApiModel(value = "AccountResultDto", description = "用户视图模型")
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * uuid
     */
    @ApiModelProperty("uuid")
    private String uuid;

    /**
     * 租户宿主端
     */
    @ApiModelProperty(value = "租户宿主端", hidden = true)
    private TenancyHostSide multiTenancySide;

    /**
     * 租户ID
     */
    @ApiModelProperty("租户ID")
    private Long tenantId;

    /**
     * 租户编号
     */
    @ApiModelProperty("tenant")
    private String tenant;

    /**
     * 租户名称
     */
    @ApiModelProperty("tenantName")
    private String tenantName;

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
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String nickName;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 盐
     */
    @ApiModelProperty("盐")
    private String salt;

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
    private Date lastLoginTime;

    /**
     * 别名(用于推送)
     */
    @ApiModelProperty(value = "别名(用于推送)")
    private String alias;

}
