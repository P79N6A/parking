package com.zoeeasy.cloud.pms.platform.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingLotConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author AkeemSuper
 * @date 2018/11/19 0019
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkVehicleDeleteByLotRequestDto", description = "删除泊位上的在停车辆")
public class ParkVehicleDeleteByLotRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场id
     */
    @ApiModelProperty(value = "停车场id", required = true)
    @NotNull(message = ParkingConstant.PARKING_INFO_PARKING_ID_NOT_NULL)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parkingId;

    /**
     * 泊位id
     */
    @ApiModelProperty(value = "泊位id", required = true)
    @NotNull(message = ParkingLotConstant.PARKING_LOT_ID_NOT_NULL)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parkingLotId;

    /**
     * 在停车辆Id
     */
    @ApiModelProperty(value = "在停车辆Id", required = true)
    @NotNull(message = ParkingLotConstant.PARK_VEHICLE_ID_NOT_EMPTY)
    private Long parkVehicleId;

}
