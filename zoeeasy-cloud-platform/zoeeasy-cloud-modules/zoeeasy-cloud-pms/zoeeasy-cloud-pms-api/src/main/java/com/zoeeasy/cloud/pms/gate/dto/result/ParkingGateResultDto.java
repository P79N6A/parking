package com.zoeeasy.cloud.pms.gate.dto.result;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 出入口视图模型
 *
 * @author Kane
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingGateResultDto", description = "出入口视图模型")
public class ParkingGateResultDto extends AuditedEntityDto<Long> {

    /**
     *
     */
    @ApiModelProperty(value = "省名称")
    private String provinceName;

    /**
     *
     */

    @ApiModelProperty(value = "市名称")
    private String cityName;
    /**
     *
     *
     */
    @ApiModelProperty(value = "自定义区域名称")
    private String customName;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    private Long parkingId;
    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场名称")
    private String parkingName;
    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场编号")
    private String parkingCode;
    /**
     * 车库ID
     */
    @ApiModelProperty("车库ID")
    private Long garageId;

    /**
     *
     */
    @ApiModelProperty("车库名称")
    private String garageName;
    /**
     * 出入口编号
     */
    @ApiModelProperty("出入口编号")
    private String code;
    /**
     * 出入口名称
     */
    @ApiModelProperty("出入口名称")
    private String name;
    /**
     * 出入口方向:
     * 1.入口
     * 2.出口
     * 3.出入口
     */
    @ApiModelProperty("出入口类型")
    private Integer direction;

    /**
     * 车道数量
     */
    @ApiModelProperty("车道数量")
    private Integer laneCount;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;
}
