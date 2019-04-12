package com.zhuyitech.parking.ucc.dto.result.user;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.jackson.annotation.ImageUrl;
import com.scapegoat.infrastructure.jackson.annotation.SensitiveInfo;
import com.scapegoat.infrastructure.jackson.enums.SensitiveType;
import com.zhuyitech.parking.common.constant.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 当前登录用户信息视图
 *
 * @author walkman
 */
@ApiModel(value = "CurrentUserResultDto", description = "当前登录用户信息视图")
@Data
@EqualsAndHashCode(callSuper = true)
public class CurrentUserResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * UUID
     */
    @ApiModelProperty("UUID")
    private String uuid;

    /**
     * 用户名称
     */
    @ApiModelProperty("用户名称")
    @SensitiveInfo(SensitiveType.USER_NAME)
    private String username;

    /**
     * 用户昵称
     */
    @ApiModelProperty("用户昵称")
    private String nickname;

    /**
     * 真实姓名
     */
    @ApiModelProperty("真实姓名")
    @SensitiveInfo(SensitiveType.CHINESE_NAME)
    private String realName;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private Integer gender;

    /**
     * 出生日期
     */
    @ApiModelProperty("出生日期")
    private Date birthday;

    /**
     * 实名状态
     */
    @ApiModelProperty("实名状态")
    private Integer authStatus;

    /**
     * 用户等级
     */
    @ApiModelProperty("用户等级")
    private Integer level;

    /**
     * 手机号码
     */
    @ApiModelProperty("手机号码")
    @SensitiveInfo(SensitiveType.MOBILE_PHONE)
    private String phoneNumber;

    /**
     * 电子邮箱
     */
    @ApiModelProperty("电子邮箱")
    @SensitiveInfo(SensitiveType.EMAIL_ADDRESS)
    private String emailAddress;

    /**
     * 用户类型
     */
    @ApiModelProperty("用户类型")
    private Integer userType;

    /**
     * 用户状态
     */
    @ApiModelProperty("用户状态")
    private Integer status;

    /**
     * 上次登录时间
     */
    @ApiModelProperty("上次登录时间")
    private Date lastLoginTime;

    /**
     * 角色列表
     */
    @ApiModelProperty("角色列表")
    private List<String> roles;

    /**
     * 个人介绍
     */
    @ApiModelProperty(" introduction")
    private String introduction;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    @ImageUrl(value = Const.IMAGE_URL_PREFIX)
    private String portrait;

}
