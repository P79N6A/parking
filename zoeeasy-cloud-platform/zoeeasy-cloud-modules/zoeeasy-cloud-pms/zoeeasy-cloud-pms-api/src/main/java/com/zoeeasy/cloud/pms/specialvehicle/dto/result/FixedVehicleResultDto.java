package com.zoeeasy.cloud.pms.specialvehicle.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 固定车视图模型
 *
 * @date: 2018/10/18.
 * @author：zm
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "FixedVehicleResultDto", description = "固定车视图模型")
public class FixedVehicleResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

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
     * 开始时间
     */
    @ApiModelProperty("开始时间,格式HH:mm:ss")
    @JsonFormat(pattern = Const.FORMAT_DATETIME)
    private Date beginTime;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间,格式HH:mm:ss")
    @JsonFormat(pattern = Const.FORMAT_DATETIME)
    private Date endTime;

    /**
     * 生效状态(1 : 未生效 2 已生效 3 已失效)
     */
    @ApiModelProperty(value = "生效状态")
    private Integer status;

    /**
     * 是否关联
     */
    @ApiModelProperty(value = "是否关联")
    private Integer relevance;

    /**
     * 停车场id
     */
    @ApiModelProperty("停车场id")
    private Long parkingId;

    /**
     * 区域名称
     */
    @ApiModelProperty("区域名称")
    private String areaName;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

    /**
     * 停车场编号
     */
    @ApiModelProperty("停车场编号")
    private String parkingCode;

    /**
     * 泊位号
     */
    @ApiModelProperty("泊位号")
    private String parkingLotNumber;

    /**
     * 泊位编号
     */
    @ApiModelProperty("泊位编号")
    private String code;
}
