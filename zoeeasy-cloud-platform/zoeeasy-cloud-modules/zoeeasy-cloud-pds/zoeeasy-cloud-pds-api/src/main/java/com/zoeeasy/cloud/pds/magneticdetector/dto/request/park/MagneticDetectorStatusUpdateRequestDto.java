package com.zoeeasy.cloud.pds.magneticdetector.dto.request.park;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import com.zoeeasy.cloud.pds.magneticdetector.cst.MagneticDetectorConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 修改地磁检测器状态
 *
 * @Date: 2018/10/15
 * @author: lhj
 */
@Data
@ApiModel(value = "MagneticDetectorStatusUpdateRequestDto", description = "维护地磁检测器状态")
@EqualsAndHashCode(callSuper=false)
public class MagneticDetectorStatusUpdateRequestDto extends SignedSessionEntityDto<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 地磁检测器当前状态，-1：未知，0：正常，4：失联
     */
    @ApiModelProperty(value = "地磁检测器当前状态，-1：未知，0：正常，4：失联")
    @NotNull(message = MagneticDetectorConstant.MAGNETIC_DETECTOR_STATIC)
    private Integer status;
}
