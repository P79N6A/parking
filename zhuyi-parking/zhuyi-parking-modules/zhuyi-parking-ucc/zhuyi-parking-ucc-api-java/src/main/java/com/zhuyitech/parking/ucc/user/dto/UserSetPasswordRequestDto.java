package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户设置登录密码请求参数
 *
 * @author walkman
 */
@ApiModel(value = "UserResetPasswordRequestDto", description = "用户设置登录密码请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserSetPasswordRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

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

}
