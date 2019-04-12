package com.zoeeasy.cloud.ucc.tenant.dto.request;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.ucc.common.UccConstant;
import com.zoeeasy.cloud.ucc.tenant.cst.TenantConstant;
import com.zoeeasy.cloud.ucc.tenant.validator.TenantNameValidator;
import com.zoeeasy.cloud.ucc.tenant.validator.TenantTypeValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 租户创建请求参数
 *
 * @author walkman
 * @since 2018-08-28
 */
@ApiModel(value = "TenantCreateRequestDto", description = "租户创建请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class TenantCreateRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 租户名称
     */
    @NotNull(message = TenantConstant.TENANT_NAME_NOT_EMPTY)
    @Length(min = TenantConstant.TENANT_NAME_MINI_LENGTH,
            max = TenantConstant.TENANT_NAME_MAX_LENGTH,
            message = TenantConstant.TENANT_NAME_LENGTH_RANGE)
    @Pattern(regexp = TenantConstant.TENANT_NAME_PATTERN, message = TenantConstant.TENANT_NAME_ILLEGAL)
    @FluentValidate({TenantNameValidator.class})
    private String name;

    /**
     * 租户类型
     */
    @ApiModelProperty(value = "租户类型")
    @FluentValidate({TenantTypeValidator.class})
    private Integer type;

    /**
     * 租户URL
     */
    @ApiModelProperty(value = "url")
    private String url;

    /**
     * 租户域名
     */
    @ApiModelProperty(value = "租户域名")
    private String domain;

    /**
     * 租户LOGO
     */
    @ApiModelProperty(value = "租户LOGO")
    private String logo;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介")
    private String description;

    /**
     * 管理员账号,平台唯一
     */
    @ApiModelProperty(value = "管理员账号")
    @NotBlank(message = UccConstant.ACCOUNT_NAME_NOT_EMPTY)
    @Length(min = UccConstant.ACCOUNT_NAME_MINI_LENGTH,
            max = UccConstant.ACCOUNT_NAME_MAX_LENGTH,
            message = UccConstant.ACCOUNT_NAME_LENGTH_RANGE)
    @Pattern(regexp = UccConstant.ACCOUNT_NAME_PATTERN, message = UccConstant.ACCOUNT_NAME_ILLEGAL)
    private String adminAccount;

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
