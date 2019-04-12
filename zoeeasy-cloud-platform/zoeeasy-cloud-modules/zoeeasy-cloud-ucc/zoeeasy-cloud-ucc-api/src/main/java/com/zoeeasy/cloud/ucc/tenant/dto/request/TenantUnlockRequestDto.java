package com.zoeeasy.cloud.ucc.tenant.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商户解锁请求参数
 *
 * @author walkman
 * @since 2018-08-28
 */
@ApiModel(value = "TenantUnlockRequestDto", description = "商户解锁请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class TenantUnlockRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

}

