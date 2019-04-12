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

/**
 * 用户个人信息视图
 *
 * @author walkman
 */
@ApiModel(value = "UserProfileResultDto", description = "用户个人信息视图")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserProfileResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名称
     */
    @ApiModelProperty("用户名称")
    @SensitiveInfo(SensitiveType.USER_NAME)
    private String username;

    /**
     * 真实姓名
     */
    @ApiModelProperty("真实姓名")
    @SensitiveInfo(SensitiveType.CHINESE_NAME)
    private String realName;

    /**
     * 身份证号
     */
    @ApiModelProperty("身份证号")
    @SensitiveInfo(SensitiveType.ID_CARD)
    private String cardNo;

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
     * 实名状态,0未认证 1 认证中  2 已认证  3 认证不通过
     */
    @ApiModelProperty(value = "实名状态,0未认证 1 认证中  2 已认证  3 认证不通过")
    private Integer authenticated;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private Integer gender;

    /**
     * 生日
     */
    @ApiModelProperty("生日")
    private String birthday;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    @ImageUrl(value = Const.IMAGE_URL_PREFIX)
    private String portrait;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickname;

    /**
     * 注册日期
     */
    @ApiModelProperty("注册日期")
    private String creationTime;

    /**
     * 未读通知消息条数
     */
    @ApiModelProperty(value = "未读通知消息条数(多余9条9+)")
    private String unreadCount;

    /**
     * 用户车辆个数
     */
    @ApiModelProperty(value = "用户车辆个数")
    private Integer carCount;

}
