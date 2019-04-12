package com.zoeeasy.cloud.ucc.user.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import com.zoeeasy.cloud.ucc.common.UccConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 修改用户密码请求参数
 *
 * @author walkman
 */
@ApiModel(value = "UserPasswordResetRequestDto", description = "获取用户请求参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class UserPasswordResetRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 密码
     */
    @ApiModelProperty(required = true, value = "密码")
    @NotBlank(message = UccConstant.ACCOUNT_AUTHENTICATION_NOT_EMPTY)
    @Length(min = UccConstant.ACCOUNT_AUTHENTICATION_MINI_LENGTH,
            max = UccConstant.ACCOUNT_AUTHENTICATION_MAX_LENGTH,
            message = UccConstant.ACCOUNT_AUTHENTICATION_LENGTH_RANGE)
    @Pattern(regexp = UccConstant.ACCOUNT_AUTHENTICATION_PATTERN, message = UccConstant.ACCOUNT_AUTHENTICATION_ILLEGAL)
    private String password;

    /**
     * 确认密码
     */
    @ApiModelProperty(required = true, value = "确认密码")
    @NotBlank(message = UccConstant.ACCOUNT_CONFIRM_AUTHENTICATION_NOT_EMPTY)
    @Length(min = UccConstant.ACCOUNT_AUTHENTICATION_MINI_LENGTH,
            max = UccConstant.ACCOUNT_AUTHENTICATION_MAX_LENGTH,
            message = UccConstant.ACCOUNT_AUTHENTICATION_LENGTH_RANGE)
    @Pattern(regexp = UccConstant.ACCOUNT_AUTHENTICATION_PATTERN, message = UccConstant.ACCOUNT_AUTHENTICATION_ILLEGAL)
    private String confirmPassword;

}