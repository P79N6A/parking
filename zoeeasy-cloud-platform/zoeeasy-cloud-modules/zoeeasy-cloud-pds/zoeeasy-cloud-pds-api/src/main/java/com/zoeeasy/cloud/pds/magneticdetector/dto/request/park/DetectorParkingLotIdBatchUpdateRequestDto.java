package com.zoeeasy.cloud.pds.magneticdetector.dto.request.park;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.cst.MagneticDetectorConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 批量更新设备泊位ID
 *
 * @Date: 2018/10/17
 * @author: lhj
 */
@Data
@ApiModel(value = "DetectorParkingLotIdBatchUpdateRequestDto", description = "批量更新设备泊位ID")
@EqualsAndHashCode(callSuper = false)
public class DetectorParkingLotIdBatchUpdateRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 关联泊位id
     */
    @ApiModelProperty(value = "关联泊位id")
    @NotNull(message = MagneticDetectorConstant.MAGNETIC_DETECTOR_RELEVANCE_PARKING_LOT_ID_NOT_NULL)
    private Long parkingLotId;

    /**
     * 设备检测器code
     */
    @ApiModelProperty(value = "设备检测器code")
    private String code;
}
