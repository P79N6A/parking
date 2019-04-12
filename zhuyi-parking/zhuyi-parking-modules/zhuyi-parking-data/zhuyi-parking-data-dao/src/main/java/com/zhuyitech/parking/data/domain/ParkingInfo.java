package com.zhuyitech.parking.data.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.IVersion;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author walkman
 */
@TableName("up_parking_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class ParkingInfo extends FullAuditedEntity<Long> implements IVersion<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 支付宝平台停车场ID
     */
    @TableField(value = "aliParkId")
    private String aliParkId;

    /**
     * 海康平台停车场ID
     */
    @TableField(value = "hikParkId")
    private String hikParkId;

    /**
     * 编号
     */
    @TableField(value = "code")
    private String code;

    /**
     * 简称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 全称
     */
    @TableField(value = "fullName")
    private String fullName;

    /**
     * 状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 是否接入平台
     */
    @TableField(value = "platformSupport")
    private Boolean platformSupport;

    /**
     * 用户支付未离场的超时时间(以分钟为单位)
     */
    @TableField(value = "outTime")
    private Integer outTime;

    /**
     * 停车场类型((1为地面，2为地下，3为路边)（多个类型，中间用,隔开)
     */
    @TableField(value = "type")
    private String type;

    /**
     * 停车场类型，1为小区停车场、2为商圈停车场、3为路面停车场、4为园区停车场、5为写字楼停车场、6为私人停车场
     */
    @TableField(value = "lotType")
    private String lotType;

    /**
     * LOGO
     */
    @TableField(value = "logo")
    private String logo;

    /**
     * 开始营业时间，格式HH:mm:ss
     */
    @TableField(value = "openStartTime")
    private String openStartTime;

    /**
     * 结束营业时间，格式HH:mm:ss
     */
    @TableField(value = "openEndTime")
    private String openEndTime;

    /**
     * 是否收费
     */
    @TableField(value = "chargeFee")
    private Boolean chargeFee;

    /**
     * 收费模式 1: 离场后缴费 2: 缴费后离场
     */
    @TableField(value = "chargeMode")
    private Integer chargeMode;

    /**
     * 收费方式（1为停车卡缴费，2为物料缴费，3为中央缴费机）
     */
    @TableField(value = "payMode")
    private String payMode;

    /**
     * 支付方式（1为支付宝在线缴费，2为支付宝代扣缴费，3当面付)，如支持多种方式以','进行间隔
     */
    @TableField(value = "payType")
    private String payType;

    /**
     * 收费规则
     */
    @TableField(value = "chargeRule")
    private String chargeRule;

    /**
     * 收费描述信息
     */
    @TableField(value = "chargeDescription")
    private String chargeDescription;

    /**
     * false不可预约 true可预约
     */
    @TableField(value = "supportAppointment")
    private Boolean supportAppointment;

    /**
     * 预约规则
     */
    @TableField(value = "appointmentRule")
    private String appointmentRule;

    /**
     * 总共可预约车位
     */
    @TableField(value = "lotAppointmentTotal")
    private Integer lotAppointmentTotal;

    /**
     * 剩余可预约车位
     */
    @TableField(value = "lotAppointmentAvailable")
    private Integer lotAppointmentAvailable;

    /**
     * 经度
     */
    @TableField(value = "longitude")
    private Double longitude;

    /**
     * 纬度
     */
    @TableField(value = "latitude")
    private Double latitude;

    /**
     * 省编码
     */
    @TableField(value = "provinceCode")
    private String provinceCode;

    /**
     * 市编码
     */
    @TableField(value = "cityCode")
    private String cityCode;

    /**
     * 区县编码
     */
    @TableField(value = "countyCode")
    private String countyCode;

    /**
     * 地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 邮编
     */
    @TableField(value = "zipCode")
    private String zipCode;

    /**
     * 联系人
     */
    @TableField(value = "contactName")
    private String contactName;

    /**
     * 联系电话
     */
    @TableField(value = "contactTel")
    private String contactTel;

    /**
     * 联系手机
     */
    @TableField(value = "contactPhone")
    private String contactPhone;

    /**
     * 联系人邮箱
     */
    @TableField(value = "contactEmail")
    private String contactEmail;

    /**
     * 联系人QQ
     */
    @TableField(value = "contactQQ")
    private String contactQQ;

    /**
     * 联系人微信
     */
    @TableField(value = "contactWeixin")
    private String contactWeixin;

    /**
     * 联系人支付宝
     */
    @TableField(value = "contactAlipay")
    private String contactAlipay;

    /**
     * 管理单位
     */
    @TableField(value = "managerUnit")
    private String managerUnit;

    /**
     * 所有人单位
     */
    @TableField(value = "ownerName")
    private String ownerName;

    /**
     * 运营人单位
     */
    @TableField(value = "operatorUnit")
    private String operatorUnit;

    /**
     * 收费单位
     */
    @TableField(value = "chargerUnit")
    private String chargerUnit;

    /**
     * 描述信息
     */
    @TableField(value = "description")
    private String description;

    /**
     * 车位总数
     */
    @TableField(value = "lotTotal")
    private Integer lotTotal;

    /**
     * 固定车位总数
     */
    @TableField(value = "lotFixed")
    private Integer lotFixed;

    /**
     * 可用车位数
     */
    @TableField(value = "lotAvailable")
    private Integer lotAvailable;

    /**
     * 距离
     */
    @TableField(exist = false)
    private Double distance;

    /**
     * 空闲等级(1:绿色 2: 黄色 3: 红色)
     */
    @TableField(exist = false)
    private Integer freeLevel;

    /**
     * 创建者
     */
    @TableField(value = "creatorUserId", fill = FieldFill.INSERT)
    protected Long creatorUserId;

    /**
     * 创建日期
     */
    @TableField(value = "creationTime", fill = FieldFill.INSERT)
    protected Date creationTime;

    /**
     * 更新者
     */
    @TableField(value = "lastModifierUserId", fill = FieldFill.INSERT_UPDATE)
    protected Long lastModifierUserId;

    /**
     * 更新日期
     */
    @TableField(value = "lastModificationTime", fill = FieldFill.INSERT_UPDATE)
    protected Date lastModificationTime;

    /**
     * 删除者
     */
    @TableField(value = "deleterUserId", fill = FieldFill.UPDATE)
    protected Long deleterUserId;

    /**
     * 删除日期
     */
    @TableField(value = "deletionTime", fill = FieldFill.UPDATE)
    protected Date deletionTime;

    /**
     * 删除标记（0：正常；1：删除 ）
     */
    @TableField(value = "deleted", fill = FieldFill.INSERT)
    @TableLogic
    protected Integer deleted;

    /**
     * 版本号
     */
    @Version
    private Long version;

}

