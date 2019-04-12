package com.zoeeasy.cloud.ucc.tenant.dto.request;

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
 * 租户管理员密码重置请求参数
 *
 * @author walkman
 * @since 2018-08-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TenantPasswordResetRequestDto", description = "租户编辑请求参数")
public class TenantPasswordResetRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员密码
     */
    @ApiModelProperty(value = "管理员密码")
    @NotBlank(message = UccConstant.ACCOUNT_AUTHENTICATION_NOT_EMPTY)
    @Length(min = UccConstant.ACCOUNT_AUTHENTICATION_MINI_LENGTH,
            max = UccConstant.ACCOUNT_AUTHENTICATION_MAX_LENGTH,
            message = UccConstant.ACCOUNT_AUTHENTICATION_LENGTH_RANGE)
    @Pattern(regexp = UccConstant.ACCOUNT_AUTHENTICATION_PATTERN, message = UccConstant.ACCOUNT_AUTHENTICATION_ILLEGAL)
    private String adminPassword;

    /**
     * 管理员确认密码
     */
    @ApiModelProperty(value = "管理员确认密码")
    @NotBlank(message = UccConstant.ACCOUNT_CONFIRM_AUTHENTICATION_NOT_EMPTY)
    @Length(min = UccConstant.ACCOUNT_AUTHENTICATION_MINI_LENGTH,
            max = UccConstant.ACCOUNT_AUTHENTICATION_MAX_LENGTH,
            message = UccConstant.ACCOUNT_AUTHENTICATION_LENGTH_RANGE)
    @Pattern(regexp = UccConstant.ACCOUNT_AUTHENTICATION_PATTERN, message = UccConstant.ACCOUNT_AUTHENTICATION_ILLEGAL)
    private String adminConfirmedPassword;

}