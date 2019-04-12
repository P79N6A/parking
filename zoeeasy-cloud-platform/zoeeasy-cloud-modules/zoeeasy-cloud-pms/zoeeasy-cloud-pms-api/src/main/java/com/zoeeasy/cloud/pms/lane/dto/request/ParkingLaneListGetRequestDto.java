package com.zoeeasy.cloud.pms.lane.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 车道列表请求参数表
 *
 * @author Kane
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingLaneListGetRequestDto", description = "车道列表请求参数表")
public class ParkingLaneListGetRequestDto extends SignedSessionEntityDto<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    private Long parkingId;

    /**
     * 车道名称
     */
    @ApiModelProperty("车道名称")
    private String name;

}
