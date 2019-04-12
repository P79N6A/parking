package com.zoeeasy.cloud.invoice.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 发票订单详情表(inv_invoice_order_detail)表实体类
 *
 * @author AkeemSuper
 * @date 2019-02-20 17:31:16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("inv_invoice_order_detail")
public class InvoiceOrderDetailEntity extends FullAuditedEntity<Long> implements Serializable {

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
     * 发票订单号
     */
    @TableField("invoiceOrderNo")
    private String invoiceOrderNo;

    /**
     * 平台停车账单流水号
     */
    @TableField("orderNo")
    private String orderNo;

    /**
     * 平台停车场ID
     */
    @TableField("parkingId")
    private Long parkingId;

    /**
     * 停车场名称
     */
    @TableField("parkingName")
    private String parkingName;

    /**
     * 车牌号
     */
    @TableField("plateNumber")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @TableField("plateColor")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @TableField("carStyle")
    private Integer carStyle;

    /**
     * 停车开始时间
     */
    @TableField("startTime")
    private Date startTime;

    /**
     * 停车结束时间
     */
    @TableField("endTime")
    private Date endTime;

    /**
     * 停车时长(秒)
     */
    @TableField("parkingLength")
    private Long parkingLength;

    /**
     * 开票金额(分)
     */
    @TableField("invoiceAmount")
    private Integer invoiceAmount;

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

}