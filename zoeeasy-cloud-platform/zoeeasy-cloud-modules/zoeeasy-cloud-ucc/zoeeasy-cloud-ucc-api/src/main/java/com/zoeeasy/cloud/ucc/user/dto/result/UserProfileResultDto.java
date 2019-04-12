package com.zoeeasy.cloud.ucc.user.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.jackson.annotation.SensitiveInfo;
import com.scapegoat.infrastructure.jackson.enums.SensitiveType;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

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
     * 用户账号
     */
    @ApiModelProperty("用户账号")
    private String account;

    /**
     * 用户名称
     */
    @ApiModelProperty("用户名称")
    @SensitiveInfo(SensitiveType.USER_NAME)
    private String userName;

    /**
     * 真实姓名
     */
    @ApiModelProperty("真实姓名")
    @SensitiveInfo(SensitiveType.CHINESE_NAME)
    private String realName;

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
     * 头像
     */
    @ApiModelProperty("头像")
    private String portrait;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickName;

    /**
     * 注册日期
     */
    @ApiModelProperty("注册日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date creationTime;

    /**
     * 商户名称
     */
    @ApiModelProperty("注册日期")
    private String tenantName;

}
