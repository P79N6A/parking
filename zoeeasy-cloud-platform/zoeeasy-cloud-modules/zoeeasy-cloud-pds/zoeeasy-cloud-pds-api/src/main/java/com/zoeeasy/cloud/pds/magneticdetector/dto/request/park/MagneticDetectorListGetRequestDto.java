package com.zoeeasy.cloud.pds.magneticdetector.dto.request.park;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取设备列表请求参数
 *
 * @author lhj
 */
@Data
@ApiModel(value = "MagneticDetectorListGetRequestDto", description = "获取设备列表请求参数")
@EqualsAndHashCode(callSuper=false)
public class MagneticDetectorListGetRequestDto extends SignedSessionEntityDto<Long> {
    private static final long serialVersionUID = 1L;

    /**
     * 地磁检测器编号(平台唯一)
     */
    @ApiModelProperty(value = "地磁检测器编号")
    private String code;

    /**
     *地磁管理器类型(厂商) 数据字典
     */
    @ApiModelProperty(value = "地磁管理器类型(厂商)")
    private Integer provider;

    /**
     *停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    private Long parkingId;

    /**
     * 泊位ID
     */
    @ApiModelProperty(value = "泊位ID")
    private Long parkingLotId;

    /**
     *关联地磁管理器id
     */
    @ApiModelProperty(value = "关联地磁管理器id")
    private Long managerId;
}
