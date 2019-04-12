package com.zoeeasy.cloud.pms.platform.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author dzc
 * @date 2019-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingVehicleRecordViewResultDto", description = "停车场在停车辆视图")
public class ParkingVehicleRecordLotResultDto extends EntityDto<Long>{
    private static final long serialVersionUID = 1L;

    /**
     * 车场ID
     */
    @ApiModelProperty("车场ID")
    private Long parkingId;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;


}
