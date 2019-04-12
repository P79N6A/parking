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
 * 登录用户结果视图
 *
 * @author walkman
 */
@ApiModel(value = "UserLoginResultDto", description = "登录用户结果视图")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserLoginResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 用户名称
     */
    @ApiModelProperty("用户名称")
    @SensitiveInfo(SensitiveType.USER_NAME)
    private String username;

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
     */
    @ApiModelProperty("portrait")
    @ImageUrl(value = Const.IMAGE_URL_PREFIX)
    private String portrait;

    /**
     * 登录token
     */
    @ApiModelProperty("登录token")
    private String accessToken;

    /**
     * refreshToken
     */
    @ApiModelProperty("refreshToken")
    private String refreshToken;

    /**
     * tokenType
     */
    @ApiModelProperty("tokenType")
    private String tokenType;

    /**
     * expiresIn
     */
    @ApiModelProperty("有效时间(秒)")
    private Integer expiresIn;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickname;

    /**
     * 推送标签列表
     */
    @ApiModelProperty("推送标签列表")
    private List<String> tags;

    /**
     * 推送别称
     */
    @ApiModelProperty("推送别称")
    private String alias;

    /**
     * 车辆数量
     */
    @ApiModelProperty("车辆数量")
    private Integer carCount;

}
