package com.zoeeasy.cloud.pms.park.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询泊位配置相关参数返回
 *
 * @Date: 2018/10/16
 * @author: lhj
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "MagneticDetectorByParkingLotQueryPageResultDto", description = "分页查询泊位配置相关参数返回")
public class MagneticDetectorByParkingLotQueryPageResultDto extends BaseDto {
    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Long id;

    /**
     * 泊位编号
     */
    @ApiModelProperty(value = "泊位编号")
    private String parkingLotCode;

    /**
     * 泊位号
     */
    @ApiModelProperty(value = "泊位号")
    private String parkingLotNumber;

    /**
     * 停车场名称
     */
    @ApiModelProperty(value = "停车场名称")
    private String parkingName;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    private Long parkingId;

    /**
     * 已关联设备编号
     */
    @ApiModelProperty(value = "已关联设备编号")
    private String code;
}
