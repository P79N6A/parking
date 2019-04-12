package com.zoeeasy.cloud.pms.passing.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @Description: 分页查询平台过车记录请求参数
 * @Date: 2018/2/28 0028
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PassingVehicleRecordQueryPageRequestDto", description = "分页查询平台过车记录请求参数")
public class PassingVehicleRecordGetListRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 设备编号
     */
    @ApiModelProperty(value = "设备编号")
    private String machine;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID")
    private Long parkingId;

    /**
     * 海康平台过车ID
     */
    @ApiModelProperty(value = "海康平台过车ID")
    private String hikPassingId;

    /**
     * 阿里平台过车ID
     */
    @ApiModelProperty(value = "阿里平台过车ID")
    private String aliPassingId;

    /**
     * 泊位ID
     */
    @ApiModelProperty(value = "泊位ID")
    private Long parkingLotId;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型")
    private Integer carType;

    /**
     * 校对状态
     */
    @ApiModelProperty(value = "校对状态")
    private Integer proofStatus;

    /**
     * 数据来源
     */
    @ApiModelProperty(value = "数据来源")
    private Integer dataSource;

    /**
     * 过车置信度
     */
    @ApiModelProperty(value = "过车置信度")
    private Integer confidence;

    /**
     * 车牌号置信度
     */
    @ApiModelProperty(value = "设备编号")
    private Integer plateNumberConfidence;

    /**
     * 过车类型
     */
    @ApiModelProperty(value = "过车类型")
    private Integer passCarType;

}
