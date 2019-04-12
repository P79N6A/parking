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
@ApiModel(value = "ChargeFeeGetRequestDto", description = "是否收费获取请求参数")
public class ChargeFeeGetRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

}
