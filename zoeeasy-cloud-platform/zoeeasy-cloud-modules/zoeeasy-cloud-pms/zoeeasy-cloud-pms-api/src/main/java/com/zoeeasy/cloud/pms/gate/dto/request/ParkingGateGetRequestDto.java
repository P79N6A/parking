package com.zoeeasy.cloud.pms.gate.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取出入口请求参数
 *
 * @author Kane
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingGateGetRequestDto", description = "获取出入口请求参数")
public class ParkingGateGetRequestDto extends SignedSessionEntityDto<Long> {
    private static final long serialVersionUID = 1L;
}
