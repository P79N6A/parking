package com.zoeeasy.cloud.pds.magneticdetector.dto.request.inspect;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pds.magneticdetector.cst.MagneticDetectorConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author AkeemSuper
 * @date 2018/10/29 0029
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MagneticDetectorGetByInspectRequestDto", description = "巡检获取地磁检测器请求参数")
public class MagneticDetectorGetByInspectRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场id
     */
    @ApiModelProperty(value = "停车场id", required = true)
    @NotNull(message = MagneticDetectorConstant.PARKING_INFO_PARKING_ID_NOT_NULL)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parkingId;

    /**
     * 泊位id
     */
    @ApiModelProperty(value = "泊位id", required = true)
    @NotNull(message = MagneticDetectorConstant.PARKING_LOT_ID_NOT_NULL)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parkingLotId;

}
