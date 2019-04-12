package com.zoeeasy.cloud.pds.magneticmanager.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 停车设备管理器分页查询请求参数
 *
 * @Date: 2018/8/23
 * @author: wh
 */
@Data
@ApiModel(value = "MagneticManagerQueryPageRequestDto", description = "停车设备管理器分页查询请求参数")
@EqualsAndHashCode(callSuper = false)
public class MagneticManagerQueryPageRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

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
     * 地磁管理器编号
     */
    @ApiModelProperty(value = "地磁管理器编号")
    private String code;
}
