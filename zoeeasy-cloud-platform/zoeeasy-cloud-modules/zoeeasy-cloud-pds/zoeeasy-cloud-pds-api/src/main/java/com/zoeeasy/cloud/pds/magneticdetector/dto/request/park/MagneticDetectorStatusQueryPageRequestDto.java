package com.zoeeasy.cloud.pds.magneticdetector.dto.request.park;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询地磁检测器状态请求参数
 *
 * @Date: 2018/10/12
 * @author: lhj
 */
@Data
@ApiModel(value = "MagneticDetectorStatusQueryPageRequestDto", description = "分页查询地磁检测器状态请求参数")
@EqualsAndHashCode(callSuper = false)
public class MagneticDetectorStatusQueryPageRequestDto extends SignedSessionPagedRequestDto {
    private static final long serialVersionUID = 1L;

    /**
     * 区域编号
     */
    @ApiModelProperty(value = "区域编号")
    private String areaCode;

    /**
     * 停车场类型
     */
    @ApiModelProperty(value = "停车场类型")
    private String lotType;

    /**
     * 停车场
     */
    @ApiModelProperty(value = "停车场")
    private String parkingName;
}
