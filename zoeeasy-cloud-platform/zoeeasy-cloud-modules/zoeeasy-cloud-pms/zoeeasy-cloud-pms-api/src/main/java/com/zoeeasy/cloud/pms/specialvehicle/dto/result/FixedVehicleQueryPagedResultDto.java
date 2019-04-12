package com.zoeeasy.cloud.pms.specialvehicle.dto.result;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 固定车列表视图模型
 *
 * @date: 2018/10/18.
 * @author：zm
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "FixedVehicleQueryPagedResultDto", description = "固定车列表视图模型")
public class FixedVehicleQueryPagedResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场id
     */
    @ApiModelProperty("停车场id")
    private Long parkingId;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

    /**
     * 车牌号码
     */
    @ApiModelProperty(value = "车牌编号")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色")
    private Integer plateColor;

    /**
     * 车牌类型
     */
    @ApiModelProperty(value = "车牌类型")
    private String plateType;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型")
    private Integer carType;

    /**
     * 车辆颜色
     */
    @ApiModelProperty(value = "车辆颜色")
    private Integer carColor;

    /**
     * 车主姓名
     */
    @ApiModelProperty(value = "车主姓名")
    private String ownerName;

    /**
     * 车主手机
     */
    @ApiModelProperty(value = "车主手机")
    private String ownerPhone;

    /**
     * 固定车类型
     */
    @ApiModelProperty(value = "固定车类型")
    private Integer fixedType;

    /**
     * 开始时间 - 结束时间 有效时期
     */
    @ApiModelProperty("开始时间 - 结束时间 有效时期")
    private String validTime;

    /**
     * 生效状态(1 : 未生效 2 已生效 3 已失效)
     */
    @ApiModelProperty(value = "生效状态")
    private String status;

    /**
     * 是否关联泊位
     */
    @ApiModelProperty(value = "是否关联")
    private String relevance;
}