package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 停车场基本信息更新
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingInfoUpdateRequestDto", description = "根据ID获取停车场")
public class ParkingInfoUpdateRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 区域ID
     */
    private Long areaId;

    /**
     * 区域ID路径
     */
    private String pathCode;

    /**
     * 支付宝平台停车场ID
     */
    private String aliParkId;

    /**
     * 海康平台停车场ID
     */
    private String hikParkId;

    /**
     * 编号
     */
    private String code;

    /**
     * 客户端编号
     */
    private String localCode;

    /**
     * 场库对接ID
     */
    private Long dockId;

    /**
     * 简称
     */
    private String name;

    /**
     * 全称
     */
    private String fullName;

    /**
     * 在线状态,0-下线,1-在线
     */
    private Integer status;

    /**
     * 是否平台接入 0:否 1 是
     */
    private Integer platformSupport;

    /**
     * 停车场等级，0-其它，1-一级停车场，2-二级停车场，3-三级停车场
     */
    private Integer level;

    /**
     * 用户支付未离场的超时时间(以分钟为单位)
     */
    private Integer outTime;

    /**
     * 停车场类型((1为地面，2为地下，3为路边)（多个类型，中间用,隔开)
     */
    private String type;

    /**
     * 停车场类型，1为小区停车场、2为商圈停车场、3为路面停车场、4为园区停车场、5为写字楼停车场、6为私人停车场
     */
    private String lotType;

    /**
     * LOGO
     */
    private String logo;

    /**
     * 开始营业时间，格式HH:mm:ss
     */
    private String openStartTime;

    /**
     * 结束营业时间，格式HH:mm:ss
     */
    private String openEndTime;

    /**
     * 缴费模式 1: 离场后缴费 2: 缴费后离场
     */
    private Integer chargeMode;

    /**
     * 收费方式（1为停车卡缴费，2为物料缴费，3为中央缴费机）
     */
    private String payMode;

    /**
     * 支付方式（1为支付宝在线缴费，2为支付宝代扣缴费，3当面付)，如支持多种方式以'',''进行间隔
     */
    private String payType;

    /**
     * 是否支持预约
     */
    private Boolean supportAppointment;

    /**
     * 是否收费：
     * 0.不收费  1.收费
     */
    private Boolean chargeFee;

    /**
     * 纬度latitude
     */
    private Double latitude;

    /**
     * 经度longitude
     */
    private Double longitude;

    /**
     * 省编码
     */
    private String provinceCode;

    /**
     * 市编码
     */
    private String cityCode;

    /**
     * 区县编码
     */
    private String countyCode;

    /**
     * 地址
     */
    private String address;

    /**
     * 车位总数
     */
    private Integer lotTotal;

    /**
     * 固定车总数
     */
    private Integer lotFixed;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 审核状态 1待审核2通过3未通过
     */
    private Integer auditStatus;

    /**
     * 审核人员Id
     */
    private Long auditUserId;

    /**
     * 审核人员名称
     */
    private String auditUserName;

    /**
     * 审核时间
     */
    private Date auditTime;

    /**
     * 上下线状态
     */
    private Integer runStatus;
}
