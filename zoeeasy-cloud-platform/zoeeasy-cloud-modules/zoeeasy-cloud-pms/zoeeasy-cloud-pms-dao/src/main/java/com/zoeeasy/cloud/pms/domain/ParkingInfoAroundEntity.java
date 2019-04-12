package com.zoeeasy.cloud.pms.domain;

import com.baomidou.mybatisplus.annotations.*;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * 停车场基本信息
 *
 * @author walkman
 */
@TableName("pms_parking_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingInfoAroundEntity extends Entity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    @TableField("tenantId")
    private Long tenantId;

    /**
     * 简称
     */
    @TableField("name")
    private String name;

    /**
     * 全称
     */
    @TableField("fullName")
    private String fullName;

    /**
     * 在线状态,0-下线,1-在线
     */
    @TableField("status")
    private Integer status;

    /**
     * 是否平台接入 0:否 1 是
     */
    @TableField("platformSupport")
    private Integer platformSupport;

    /**
     * 停车场等级，0-其它，1-一级停车场，2-二级停车场，3-三级停车场
     */
    @TableField("level")
    private Integer level;

    /**
     * 是否收费：
     * 0.不收费  1.收费
     */
    @TableField("chargeFee")
    private Boolean chargeFee;

    /**
     * 免费停车时长:单位分钟
     */
    @TableField("freeTime")
    private Integer freeTime;

    /**
     * 是否支持预约
     */
    @TableField("supportAppointment")
    private Boolean supportAppointment;

    /**
     * 用户支付未离场的超时时间(以分钟为单位)
     */
    @TableField("outTime")
    private Integer outTime;

    /**
     * 停车场类型((1为地面，2为地下，3为路边)（多个类型，中间用,隔开)
     */
    @TableField("type")
    private String type;

    /**
     * 停车场类型，1为小区停车场、2为商圈停车场、3为路面停车场、4为园区停车场、5为写字楼停车场、6为私人停车场
     */
    @TableField("lotType")
    private String lotType;

    /**
     * 开始营业时间，格式HH:mm:ss
     */
    @TableField("openStartTime")
    private String openStartTime;

    /**
     * 结束营业时间，格式HH:mm:ss
     */
    @TableField("openEndTime")
    private String openEndTime;

    /**
     * 缴费模式 1: 离场后缴费 2: 缴费后离场
     */
    @TableField("chargeMode")
    private Integer chargeMode;

    /**
     * 收费方式（1为停车卡缴费，2为物料缴费，3为中央缴费机）
     */
    @TableField("payMode")
    private String payMode;

    /**
     * 支付方式（1为支付宝在线缴费，2为支付宝代扣缴费，3当面付)，如支持多种方式以'',''进行间隔
     */
    @TableField("payType")
    private String payType;

    /**
     * 纬度latitude
     */
    @TableField("latitude")
    private Double latitude;

    /**
     * 经度longitude
     */
    @TableField("longitude")
    private Double longitude;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 车位总数
     */
    @TableField("lotTotal")
    private Integer lotTotal;

    /**
     * 固定车总数
     */
    @TableField("lotFixed")
    private Integer lotFixed;

}
