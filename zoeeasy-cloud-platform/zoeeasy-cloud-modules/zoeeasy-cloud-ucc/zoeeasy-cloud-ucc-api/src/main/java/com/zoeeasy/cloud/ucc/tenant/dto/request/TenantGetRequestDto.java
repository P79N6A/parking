package com.zoeeasy.cloud.ucc.tenant.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 租户获取请求参数
 *
 * @author walkman
 * @since 2018-08-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TenantGetRequestDto", description = "租户获取请求参数")
public class TenantGetRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

}
