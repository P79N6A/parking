package com.zoeeasy.cloud.integration.common.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by zwq on 2018/12/05.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PayLimitListGetRequestDto", description = "获取预约支付时限列表请求参数")
public class PayLimitListGetRequestDto extends SignedRequestDto {

    private static final Long serialVersionUID = 1L;

}
