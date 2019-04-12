package com.zoeeasy.cloud.pds.magneticdetector.dto.request.park;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询设备列表请求参数
 *
 * @author lhj
 */
@Data
@ApiModel(value = "MagneticDetectorQueryPagedResultRequestDto", description = "分页获取设备列表请求参数")
@EqualsAndHashCode(callSuper=false)
public class MagneticDetectorQueryPagedResultRequestDto extends SignedSessionPagedRequestDto {
    private static final long serialVersionUID = 1L;

    /**
     * 地磁检测器编号
     */
    @ApiModelProperty(value = "地磁检测器序编号")
    private String code;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    private Long parkingId;

    /**
     * 区域编号
     */
    @ApiModelProperty(value = "区域编号")
    private String areaCode;
}
