package com.zoeeasy.cloud.order.domain;

import com.baomidou.mybatisplus.annotations.*;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.IVersion;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author AkeemSuper
 * @since 2018/9/29 0029
 */
@TableName("ord_third_parking_order")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class ThirdParkingOrderEntity extends FullAuditedEntity<Long> implements Serializable, IVersion<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户Id
     */
    @TableField(value = "tenantId")
    private Long tenantId;

    /**
     * 平台停车记录编号
     */
    @TableField(value = "recordNo")
    private String recordNo;

    /**
     * 平台停车账单编号
     */
    @TableField(value = "orderNo")
    private String orderNo;

    /**
     * 第三方账单编号
     */
    @TableField(value = "billNo")
    private String billNo;

    /**
     * 平台停车场ID
     */
    @TableField(value = "parkingId")
    private Long parkingId;

    /**
     * 车牌号
     */
    @TableField(value = "plateNumber")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @TableField(value = "plateColor")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @TableField(value = "carType")
    private Integer carType;

    /**
     * 停车场编号
     */
    @TableField(value = "parkCode")
    private String parkCode;

    /**
     * 车辆进场时间
     */
    @TableField(value = "enterTime")
    private Date enterTime;

    /**
     * 结算时间
     */
    @TableField(value = "costTime")
    private Date costTime;

    /**
     * 停车时长(分钟)
     */
    @TableField(value = "parkPeriodTime")
    private Integer parkPeriodTime;

    /**
     * 总收费金额(单位：分)
     */
    @TableField(value = "totalCost")
    private Integer totalCost;

    /**
     * 支付时间
     */
    @TableField(value = "payTime")
    private Date payTime;

    /**
     * 支付金额(单位：分)
     */
    @TableField(value = "payAmount")
    private Integer payAmount;

    /**
     * 支付方式 4支付宝5	微信11账户余额（云平台的账户）
     */
    @TableField(value = "payType")
    private Integer payType;

    /**
     * 账单状态
     */
    @TableField(value = "status")
    private Integer status;

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
