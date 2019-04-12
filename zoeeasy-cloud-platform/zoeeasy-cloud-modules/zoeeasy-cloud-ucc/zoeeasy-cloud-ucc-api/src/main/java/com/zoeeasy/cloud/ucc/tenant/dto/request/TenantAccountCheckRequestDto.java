package com.zoeeasy.cloud.ucc.tenant.dto.request;

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
 * 租户创建请求参数
 *
 * @author walkman
 * @since 2018-08-28
 */
@ApiModel(value = "TenantCheckRequestDto", description = "租户创建请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class TenantAccountCheckRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

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

}