package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingLotConstant;
import com.zoeeasy.cloud.pms.passing.cts.PassingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author AkeemSuper
 * @date 2018/11/14 0014
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PassVehicleUpdateParkLotRequestDto", description = "根据过车类型更新车位")
public class PassVehicleUpdateParkLotRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场id
     */
    @ApiModelProperty(value = "停车场id", required = true)
    @NotNull(message = ParkingConstant.PARKING_INFO_PARKING_ID_NOT_NULL)
    private Long parkingId;

    /**
     * 租户id
     */
    @ApiModelProperty(value = "租户id", required = true)
    @NotNull(message = ParkingConstant.TENANT_ID_NOT_EMPTY)
    private Long tenantId;

    /**
     * 泊位id
     */
    @ApiModelProperty(value = "泊位id", required = true)
    @NotNull(message = ParkingLotConstant.PARKING_LOT_ID_NOT_NULL)
    private Long parkingLotId;

    /**
     * 过车类型
     */
    @ApiModelProperty(value = "过车类型", required = true)
    @NotNull(message = PassingConstant.PASSING_TYPE_NOT_EMPTY)
    private Integer passingType;

}

