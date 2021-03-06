package com.zoeeasy.cloud.pms.gate.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 出入口列表请求参数表
 *
 * @author Kane
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingGateListGetRequestDto", description = "出入口列表请求参数表")
public class ParkingGateListGetRequestDto extends SignedSessionEntityDto<Long> {
    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    private Long parkingId;

    /**
     * 出入口名称
     */
    @ApiModelProperty("出入口名称")
    private String name;

}
