package com.zoeeasy.cloud.pms.park.dto.result;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 停车场列表视图模型
 * Created by song on 2018/9/19.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingListResultDto", description = "停车场列表视图模型")
public class ParkingListResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @ApiModelProperty("code")
    private String code;

    /**
     * 简称
     */
    @ApiModelProperty("name")
    private String name;

    /**
     * 全称
     */
    @ApiModelProperty("fullName")
    private String fullName;

    /**
     * 停车场状态 0：可用，1：不可用
     */
    @ApiModelProperty("停车场状态 0：可用，1：不可用")
    private Integer operationState;

    /**
     * 停车场类型，1为小区停车场、2为商圈停车场、3为路面停车场、4为园区停车场、5为写字楼停车场、6为私人停车场
     */
    @ApiModelProperty("停车场类型，1为小区停车场、2为商圈停车场、3为路面停车场、4为园区停车场、5为写字楼停车场、6为私人停车场")
    private String lotType;

    /**
     * 开始营业时间，格式HH:mm:ss
     */
    @ApiModelProperty("开始营业时间，格式HH:mm:ss")
    private String openStartTime;

    /**
     * 结束营业时间，格式HH:mm:ss
     */
    @ApiModelProperty("结束营业时间，格式HH:mm:ss")
    private String openEndTime;

    /**
     * 是否收费 0:不收费 1 收费
     */
    @ApiModelProperty("是否收费 0:不收费 1 收费")
    private Boolean chargeFee;

    /**
     * 纬度
     */
    @ApiModelProperty("纬度")
    private String latitude;

    /**
     * 经度
     */
    @ApiModelProperty("经度")
    private String longitude;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String address;

    /**
     * 车位总数
     */
    @ApiModelProperty("车位总数")
    private Integer lotTotal;

    /**
     * 固定车总数
     */
    @ApiModelProperty("固定车总数")
    private Integer lotFixed;

    /**
     * 可用车位数
     */
    @ApiModelProperty("可用车位数")
    private Integer lotAvailable;

    /**
     * 是否可预约
     */
    @ApiModelProperty("是否可预约")
    private Boolean supportAppointment;

    /**
     * 可预约车位总数
     */
    @ApiModelProperty("可预约车位总数")
    private Integer lotAppointmentTotal;

    /**
     * 剩余预约车位数
     */
    @ApiModelProperty("剩余预约车位数")
    private Integer lotAppointmentAvailable;

    /**
     * 缴费方式（1为停车卡缴费，2为物料缴费，3为中央缴费机）
     */
    @ApiModelProperty("缴费方式（1为停车卡缴费，2为物料缴费，3为中央缴费机）")
    private String payMode;

    /**
     * 缴费模式 1: 离场后缴费 2: 缴费后离场
     */
    @ApiModelProperty("缴费模式 1: 离场后缴费 2: 缴费后离场")
    private Integer chargeMode;

    /**
     * 上架状态: 1未上架 2上架审核中 3上架处理中 4已上架 5下架审核中 6下架处理中 7已下架 8上架申请驳回 9下架申请驳回
     */
    @ApiModelProperty("上架状态: 1未上架 2上架审核中 3上架处理中 4已上架 5下架审核中 6下架处理中 7已下架 8上架申请驳回 9下架申请驳回")
    private Integer runStatus;

    /**
     * 上下线操作时间
     */
    @ApiModelProperty("上下线操作时间")
    private Date runOperateTime;

    /**
     * 操作状态: 1申请上架 2申请下架 3可撤销申请 4不可撤销申请
     */
    @ApiModelProperty("操作状态: 1申请上架 2申请下架 3可撤销申请 4不可撤销申请")
    private Integer operateStatus;

}
