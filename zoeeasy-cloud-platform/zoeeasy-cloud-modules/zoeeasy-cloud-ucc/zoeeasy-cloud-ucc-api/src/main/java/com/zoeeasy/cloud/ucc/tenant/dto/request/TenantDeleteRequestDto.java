package com.zoeeasy.cloud.ucc.tenant.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 租户删除请求参数
 *
 * @author walkman
 * @since 2018-08-28
 */
@ApiModel(value = "TenantDeleteRequestDto", description = "租户删除请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class TenantDeleteRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

}
