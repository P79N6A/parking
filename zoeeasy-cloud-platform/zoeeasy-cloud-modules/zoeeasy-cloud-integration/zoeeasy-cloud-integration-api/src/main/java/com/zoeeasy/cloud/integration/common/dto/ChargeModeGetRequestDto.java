package com.zoeeasy.cloud.integration.common.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by song on 2018/10/11.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ChargeModeGetRequestDto", description = "收费模式获取请求参数")
public class ChargeModeGetRequestDto extends SignedRequestDto {

    private static final Long serialVersionUID = 1L;

}
