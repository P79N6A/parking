package com.zoeeasy.cloud.integration.common.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 厂商列表请求参数
 *
 * @Date: 2018/10/29
 * @author: lhj
 */
@Data
@ApiModel(value = "ProviderListRequestDto", description = "厂商列表请求参数")
@EqualsAndHashCode(callSuper = false)
public class ProviderListGetRequestDto extends SignedSessionRequestDto {
    private static final long serialVersionUID = 1L;
}
