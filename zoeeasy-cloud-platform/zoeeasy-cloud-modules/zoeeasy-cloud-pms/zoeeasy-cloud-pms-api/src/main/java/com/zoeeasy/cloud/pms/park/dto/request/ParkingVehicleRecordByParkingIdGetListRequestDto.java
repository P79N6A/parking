package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 根据停车场Id，泊位Id获取在停车辆请求参数
 *
 * @Date: 2018/11/16
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingVehicleRecordByParkingIdGetListRequestDto", description = "根据停车场Id，泊位Id获取在停车辆请求参数")
public class ParkingVehicleRecordByParkingIdGetListRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场id
     */
    @ApiModelProperty(value = "停车场id")
    private Long parkingId;

    /**
     * 泊位id
     */
    @ApiModelProperty(value = "泊位id")
    private Long parkingLotId;
}
