package com.zoeeasy.cloud.pds.magneticdetector.dto.request.park;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.cst.MagneticDetectorConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 分页查询已关联泊位设备请求参数
 *
 * @Date: 2018/10/16
 * @author: lhj
 */
@Data
@ApiModel(value = "MagneticDetectorRelevanceParkingLotQueryPageRequestDto", description = "分页查询已关联泊位设备请求参数")
@EqualsAndHashCode(callSuper = false)
public class MagneticDetectorRelevanceParkingLotQueryPageRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 泊位ID
     */
    @ApiModelProperty(value = "泊位ID")
    @NotNull(message = MagneticDetectorConstant.MAGNETIC_DETECTOR_RELEVANCE_PARKING_LOT_ID_NOT_NULL)
    private Long parkingLotId;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    @NotNull(message = MagneticDetectorConstant.PARKING_INFO_PARKING_ID_NOT_NULL)
    private Long parkingId;
}
