package com.zoeeasy.cloud.pds.magneticdetector.dto.request.park;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import com.zoeeasy.cloud.pds.magneticdetector.cst.MagneticDetectorConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 根据停车场泊位查询地磁请求参数
 *
 * @Date: 2018/10/17
 * @author: lhj
 */
@Data
@ApiModel(value = "MagneticDetectorCodeByParkingAndParkingLotRequestByIdDto", description = "根据停车场泊位查询地磁")
@EqualsAndHashCode(callSuper=false)
public class MagneticDetectorCodeByParkingAndParkingLotRequestByIdDto  extends SignedSessionEntityDto<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    @NotNull(message = MagneticDetectorConstant.PARKING_INFO_PARKING_ID_NOT_NULL)
    private Long parkingId;

    /**
     * 泊位ID
     */
    @ApiModelProperty(value = "泊位ID")
    @NotNull(message = MagneticDetectorConstant.MAGNETIC_DETECTOR_RELEVANCE_PARKING_LOT_ID_NOT_NULL)
    private Long parkingLotId;
}
