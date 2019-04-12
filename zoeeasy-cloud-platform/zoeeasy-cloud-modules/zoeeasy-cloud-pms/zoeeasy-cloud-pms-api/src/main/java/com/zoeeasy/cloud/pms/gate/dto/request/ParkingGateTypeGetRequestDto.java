package com.zoeeasy.cloud.pms.gate.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * created by kane 2018/10/15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingGateTypeGetRequestDto", description = "出入口类型获取请求参数")
public class ParkingGateTypeGetRequestDto extends SignedRequestDto {
    private static final long serialVersionUID = 1;
}
