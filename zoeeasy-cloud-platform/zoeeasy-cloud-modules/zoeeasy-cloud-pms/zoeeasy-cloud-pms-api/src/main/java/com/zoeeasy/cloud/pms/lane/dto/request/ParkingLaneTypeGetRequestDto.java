package com.zoeeasy.cloud.pms.lane.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * created by kane 2018/10/15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLaneTypeGetRequestDto", description = "车道类型获取请求参数")
public class ParkingLaneTypeGetRequestDto extends SignedRequestDto {
    private static final long serialVersionUID = 1;
}
