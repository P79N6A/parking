package com.zoeeasy.cloud.pds.magneticdetector.dto.request.park;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.cst.MagneticDetectorConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 分页获取未关联管理器设备列表请求参数
 *
 * @Date: 2018/8/30
 * @author: lhj
 */
@Data
@ApiModel(value = "MagneticDetectorNotRelevanceQueryPageRequestDto", description = "获取未关联设备列表请求参数")
@EqualsAndHashCode(callSuper = false)
public class MagneticDetectorNotRelevanceQueryPageRequestDto extends SignedSessionPagedRequestDto {
    private static final long serialVersionUID = 1L;

    /**
     * 地磁检测器编号
     */
    @ApiModelProperty("地磁检测器编号")
    private String code;

    /**
     * 停车场Id
     */
    @ApiModelProperty("停车场Id")
    @NotNull(message = MagneticDetectorConstant.PARKING_INFO_PARKING_ID_NOT_NULL)
    private Long parkingId;
}

