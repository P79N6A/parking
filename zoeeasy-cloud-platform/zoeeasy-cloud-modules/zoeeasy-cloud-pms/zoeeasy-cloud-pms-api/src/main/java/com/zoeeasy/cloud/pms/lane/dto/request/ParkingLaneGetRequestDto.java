package com.zoeeasy.cloud.pms.lane.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取车道请求参数
 *
 * @author Kane
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLaneGetRequestDto", description = "获取车道请求参数")
public class ParkingLaneGetRequestDto extends SignedSessionEntityDto<Long> {
    private static final long serialVersionUID = 1L;
}
