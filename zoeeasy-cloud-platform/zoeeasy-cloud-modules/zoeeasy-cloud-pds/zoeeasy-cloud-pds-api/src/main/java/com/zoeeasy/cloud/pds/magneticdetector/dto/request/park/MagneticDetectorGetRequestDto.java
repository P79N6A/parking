package com.zoeeasy.cloud.pds.magneticdetector.dto.request.park;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.cst.MagneticDetectorConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 获取设备请求参数
 *
 * @author lhj
 */
@Data
@ApiModel(value = "MagneticDetectorGetRequestDto", description = "获取设备请求参数")
@EqualsAndHashCode(callSuper=false)
public class MagneticDetectorGetRequestDto extends SignedSessionRequestDto {
    private static final long serialVersionUID = 1L;
    /**
     * 地磁检测器编号(平台唯一)
     */
    @ApiModelProperty(value = "地磁检测器编号(平台唯一)")
    @NotBlank(message = MagneticDetectorConstant.MAGNETIC_DETECTOR_CODE_NOT_NULL)
    private String code;

}
