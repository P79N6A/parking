package com.zoeeasy.cloud.pms.park.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 停车场视图模型
 * Created by song on 2018/9/19.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointParkingInfoResultDto", description = "停车场视图模型")
public class AppointParkingInfoResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "tenantId")
    private Long tenantId;

    /**
     * 编号
     */
    @ApiModelProperty(value = "code")
    private String code;

    /**
     * 三方停车场code
     */
    @ApiModelProperty(value = "三方停车场code")
    private String localCode;

    /**
     * 客户端id
     */
    @ApiModelProperty(value = "客户端id")
    private Long dockId;

    /**
     * 停车场简称
     */
    @ApiModelProperty(value = "停车场简称")
    private String name;

    /**
     * 停车场全称
     */
    @ApiModelProperty(value = "fullName")
    private String fullName;

    /**
     * 上下架状态
     */
    @ApiModelProperty(value = "上下架状态")
    private Integer status;

    /**
     * 是否平台接入
     */
    @ApiModelProperty("是否平台接入")
    private Boolean platformSupport;

    /**
     * 停车场等级
     */
    @ApiModelProperty(value = "停车场等级")
    private Integer level;

    /**
     * 是否收费 0:不收费 1 收费
     */
    @ApiModelProperty("是否收费 0:不收费 1 收费")
    private Boolean chargeFee;

    /**
     * 是否支持预约
     */
    @ApiModelProperty("是否支持预约")
    private Boolean supportAppointment;

    /**
     * 用户支付未离场的超时时间(以分钟为单位)
     */
    @ApiModelProperty("用户支付未离场的超时时间(以分钟为单位)")
    private Integer outTime;

    /**
     * 停车场状态 0：下线，1：上线
     */
    @ApiModelProperty("停车场状态 0：下线，1：上线")
    private Integer operationState;

    /**
     * 停车场类型，1为小区停车场、2为商圈停车场、3为路面停车场、4为园区停车场、5为写字楼停车场、6为私人停车场
     */
    @ApiModelProperty("停车场类型，1为小区停车场、2为商圈停车场、3为路面停车场、4为园区停车场、5为写字楼停车场、6为私人停车场")
    private String lotType;

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
     * 纬度
     */
    @ApiModelProperty("纬度")
    private Double latitude;

    /**
     * 经度
     */
    @ApiModelProperty("经度")
    private Double longitude;

    /**
     * 开始营业时间,格式HH:mm:ss
     */
    @ApiModelProperty("开始营业时间,格式HH:mm:ss")
    private String openStartTime;

    /**
     * 结束营业时间,格式HH:mm:ss
     */
    @ApiModelProperty("结束营业时间,格式HH:mm:ss")
    private String openEndTime;

    /**
     * 缴费方式（1为停车卡缴费，2为物料缴费，3为中央缴费机）
     */
    @ApiModelProperty("收费方式（1为停车卡缴费，2为物料缴费，3为中央缴费机）")
    private String payMode;

    /**
     * 收费模式 1: 离场后缴费 2: 缴费后离场
     */
    @ApiModelProperty("缴费模式 1: 离场后缴费 2: 缴费后离场")
    private Integer chargeMode;

    /**
     * 支付方式（1为支付宝在线缴费，2为支付宝代扣缴费，3当面付)，如支持多种方式以'',''进行间隔
     */
    @ApiModelProperty("支付方式（1为支付宝在线缴费，2为支付宝代扣缴费，3当面付)，如支持多种方式以'',''进行间隔")
    private String payType;

    /**
     * 免费停车时长：单位分钟
     */
    @ApiModelProperty("免费停车时长：单位分钟")
    private Integer freeTime;

    /**
     * LOGO
     */
    @ApiModelProperty("logo")
    private String logo;

    /**
     * 车场描述
     */
    @ApiModelProperty("车场描述")
    private String description;

    @ApiModelProperty("distance")
    private Double distance;

    @ApiModelProperty("freeLevel")
    private Integer freeLevel;
}
