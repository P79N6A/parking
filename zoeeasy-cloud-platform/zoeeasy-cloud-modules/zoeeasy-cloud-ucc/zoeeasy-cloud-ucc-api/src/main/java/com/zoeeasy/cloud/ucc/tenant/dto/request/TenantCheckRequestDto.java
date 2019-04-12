package com.zoeeasy.cloud.ucc.tenant.dto.request;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValidate;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.ucc.tenant.cst.TenantConstant;
import com.zoeeasy.cloud.ucc.tenant.validator.TenantNameValidator;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 租户创建请求参数
 *
 * @author walkman
 * @since 2018-08-28
 */
@ApiModel(value = "TenantCheckRequestDto", description = "租户创建请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class TenantCheckRequestDto extends SignedSessionRequestDto {

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

}