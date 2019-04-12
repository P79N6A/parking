package com.zoeeasy.cloud.pds.magneticdetector.dto.request.park;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取设备请求参数
 *
 * @author lhj
 */
@Data
@ApiModel(value = "MagneticDetectorParkingLotGetRequestDto", description = "获取设备请求参数")
@EqualsAndHashCode(callSuper = false)
public class MagneticDetectorParkingLotGetRequestDto extends SignedSessionRequestDto {
    private static final long serialVersionUID = 1L;
    /**
     * 泊位id
     */
    @ApiModelProperty(value = "泊位id")
    private Long parkingLotId;

}
