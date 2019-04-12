package com.zoeeasy.cloud.ucc.tenant.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商户注销请求参数
 *
 * @author walkman
 * @since 2018-08-28
 */
@ApiModel(value = "TenantCancelRequestDto", description = "商户注销请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class TenantCancelRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

}
