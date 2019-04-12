package com.zoeeasy.cloud.pms.gate.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 删除停车场进出口请求参数
 *
 * @author Kane
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingGateDeleteRequest", description = "删除停车场进出口请求参数")
public class ParkingGateDeleteRequestDto extends SignedSessionEntityDto<Long> {
    private static final long serialVersionUID = 1;
}
