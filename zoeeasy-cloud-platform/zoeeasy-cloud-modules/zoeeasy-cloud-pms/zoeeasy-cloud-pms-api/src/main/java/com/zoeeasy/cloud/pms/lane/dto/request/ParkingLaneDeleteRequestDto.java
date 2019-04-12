package com.zoeeasy.cloud.pms.lane.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 删除车道请求参数
 *
 * @author Kane
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLaneDeleteRequest", description = "删除车道请求参数")
public class ParkingLaneDeleteRequestDto extends SignedSessionEntityDto<Long> {
    private static final long serialVersionUID = 1L;

}
