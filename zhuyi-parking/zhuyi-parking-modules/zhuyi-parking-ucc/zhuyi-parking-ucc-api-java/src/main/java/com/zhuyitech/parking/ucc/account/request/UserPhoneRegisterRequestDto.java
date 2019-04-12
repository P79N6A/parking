package com.zhuyitech.parking.ucc.account.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.validate.annotaion.validation.Mobile;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户手机号注册请求参数
 *
 * @author walkman
 */
@ApiModel(value = "UserPhoneRegisterRequestDto", description = "用户手机号注册请求参数")
public class UserPhoneRegisterRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 手机号
     */
    @ApiModelProperty(required = true, value = "手机号", notes = "手机号")
    @NotBlank(message = "手机号不能为空")
    @Mobile(message = "手机号无效")
    private String phoneNumber;

    /**
     * 登录密码
     */
    @ApiModelProperty(required = true, value = "登录密码", notes = "登录密码")
    @NotBlank(message = "登录密码不能为空")
    @Length(min = 6, max = 20, message = "请输入6-20位密码")
    private String password;

    /**
     * 确认密码
     */
    @ApiModelProperty(required = true, value = "确认密码", notes = "确认密码")
    @NotBlank(message = "确认密码不能为空")
    private String confirmedPassword;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码", required = true, notes = "验证码")
    @NotBlank(message = "验证码不能为空")
    private String verifyCode;

    /**
     * 客户端类型
     * 0:未知 1:ANDROID 2:APPLE 3:H5 4:WEB
     */
    @ApiModelProperty(value = "客户端类型", required = true, notes = " 1:ANDROID 2:APPLE 3:H5 4:WEB")
    @NotNull(message = "客户端类型不能为空")
    private Integer terminateType;

    /**
     * 短信回执ID
     */
    @ApiModelProperty(value = "短信回执ID")
    private String smsRequestId;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
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

    public String getSmsRequestId() {
        return smsRequestId;
    }

    public void setSmsRequestId(String smsRequestId) {
        this.smsRequestId = smsRequestId;
    }
}
