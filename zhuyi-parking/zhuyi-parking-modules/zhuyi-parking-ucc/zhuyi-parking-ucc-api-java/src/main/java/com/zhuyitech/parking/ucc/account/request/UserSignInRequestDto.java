package com.zhuyitech.parking.ucc.account.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户登录请求参数
 *
 * @author walkman
 */
@ApiModel(description = "用户登录请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserSignInRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 登录用户名
     */
    @ApiModelProperty(value = "登录用户名或者手机号或者email，微信账号或支付宝账号", required = true)
    private String account;

    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码或者验证码")
    private String code;

    /**
     * 客户端类型
     * 0:未知 1:ANDROID 2:APPLE 3:H5 4:WEB
     */
    @NotNull
    @ApiModelProperty(value = "客户端类型", required = true, notes = " 1:ANDROID 2:APPLE 3:H5 4:WEB")
    private Integer terminateType;

    @NotNull
    @ApiModelProperty(value = "登陆方式(1:密码 2:验证码 3：微信 4：支付宝)", required = true, notes = " ")
    private Integer loginType;

    /**
     * 短信回执
     */
    @ApiModelProperty(value = "smsRequestId")
    private String smsRequestId;

}
