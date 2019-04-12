package com.zoeeasy.cloud.pds.magneticdetector.dto.request.park;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取地磁检测器列表
 *
 * @Date: 2018/10/15
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MagneticDetectorListResultRequestDto", description = "获取地磁检测器列表")
public class MagneticDetectorListResultRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 关联地磁管理器ID
     */
    @ApiModelProperty(value = "关联地磁管理器ID")
    private Long managerId;

    /**
     * 地磁检测器编号
     */
    @ApiModelProperty(value = "地磁检测器编号")
    private String code;

    /**
     * 区域code
     */
    @ApiModelProperty(value = "区域code")
    private String areaCode;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    private Long parkingId;

    /**
     * 泊位ID
     */
    @ApiModelProperty(value = "泊位ID")
    private Long parkingLotId;
}
