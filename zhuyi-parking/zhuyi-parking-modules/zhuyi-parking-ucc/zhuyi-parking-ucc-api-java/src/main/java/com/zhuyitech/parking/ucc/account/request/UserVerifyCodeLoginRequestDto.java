package com.zhuyitech.parking.ucc.account.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.validate.annotaion.validation.Mobile;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户验证码登录请求参数
 *
 * @author walkman
 */
@ApiModel(description = "用户验证码登录请求参数")
public class UserVerifyCodeLoginRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 登录手机号
     */
    @NotBlank(message = "登录手机号不能为空")
    @ApiModelProperty(value = "登录手机号", required = true)
    @Mobile(message = "手机号无效")
    private String phoneNumber;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码", required = true)
    private String verifyCode;

    /**
     * 客户端类型
     * 0:未知 1:ANDROID 2:APPLE 3:H5 4:WEB
     */
    @NotNull
    @ApiModelProperty(value = "客户端类型(1:ANDROID 2:APPLE 3:H5 4:WEB)", required = true)
    private Integer terminateType;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Integer getTerminateType() {
        return terminateType;
    }

    public void setTerminateType(Integer terminateType) {
        this.terminateType = terminateType;
    }
}
