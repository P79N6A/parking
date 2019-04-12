package com.zoeeasy.cloud.ucc.user.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.ucc.common.UccConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 修改密码参数
 *
 * @author walkman
 */
@ApiModel(description = "修改密码参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPasswordModifyRequestDto extends SignedSessionRequestDto {

    /**
     * 原密码
     */
    @ApiModelProperty(required = true, value = "原密码")
    @NotBlank(message = UccConstant.USER_OLD_AUTHENTICATION_NOT_EMPTY)
    @Length(min = UccConstant.ACCOUNT_AUTHENTICATION_MINI_LENGTH,
            max = UccConstant.ACCOUNT_AUTHENTICATION_MAX_LENGTH,
            message = UccConstant.ACCOUNT_AUTHENTICATION_LENGTH_RANGE)
    @Pattern(regexp = UccConstant.ACCOUNT_AUTHENTICATION_PATTERN, message = UccConstant.ACCOUNT_AUTHENTICATION_ILLEGAL)
    private String oldPassword;

    /**
     * 新密码
     */
    @ApiModelProperty(value = "新密码", required = true)
    @NotBlank(message = UccConstant.ACCOUNT_AUTHENTICATION_NOT_EMPTY)
    @Length(min = UccConstant.ACCOUNT_AUTHENTICATION_MINI_LENGTH,
            max = UccConstant.ACCOUNT_AUTHENTICATION_MAX_LENGTH,
            message = UccConstant.ACCOUNT_AUTHENTICATION_LENGTH_RANGE)
    @Pattern(regexp = UccConstant.ACCOUNT_AUTHENTICATION_PATTERN, message = UccConstant.ACCOUNT_AUTHENTICATION_ILLEGAL)
    private String newPassword;

    /**
     * 确认密码
     */
    @ApiModelProperty(required = true, value = "确认密码")
    @NotBlank(message = UccConstant.ACCOUNT_CONFIRM_AUTHENTICATION_NOT_EMPTY)
    @Length(min = UccConstant.ACCOUNT_AUTHENTICATION_MINI_LENGTH,
            max = UccConstant.ACCOUNT_AUTHENTICATION_MAX_LENGTH,
            message = UccConstant.ACCOUNT_AUTHENTICATION_LENGTH_RANGE)
    @Pattern(regexp = UccConstant.ACCOUNT_AUTHENTICATION_PATTERN, message = UccConstant.ACCOUNT_AUTHENTICATION_ILLEGAL)
    private String newConfirmPassword;

}
