package com.zoeeasy.cloud.integration.common.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @date: 2018/10/13.
 * @author：zm
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PacketTypeGetRequestDto", description = "包期类型获取请求参数")
public class PacketTypeGetRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;
}
