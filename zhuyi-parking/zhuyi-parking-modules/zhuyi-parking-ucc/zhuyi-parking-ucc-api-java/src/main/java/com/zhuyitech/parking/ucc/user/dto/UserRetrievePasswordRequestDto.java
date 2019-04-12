package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Date: 2018/1/2 0002
 * @author: AkeemSuper
 */
@ApiModel(value = "UserRetrievePasswordRequestDto", description = "用户找回密码之修改密码请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRetrievePasswordRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank(message = "手机号不能为空")
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
    @ApiModelProperty(value = "验证码", notes = "验证码")
    private String verifyCode;

    /**
     * 短信回执ID
     */
    @ApiModelProperty(value = "短信回执ID", notes = "短信回执ID")
    private String smsRequestId;

}
