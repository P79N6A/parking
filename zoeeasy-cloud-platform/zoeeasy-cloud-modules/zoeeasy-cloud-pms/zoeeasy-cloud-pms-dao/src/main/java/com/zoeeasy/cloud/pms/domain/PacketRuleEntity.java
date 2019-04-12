package com.zoeeasy.cloud.pms.domain;

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
 * 包期规则实体
 *
 * @date: 2018/10/13.
 * @author：zm
 */
@TableName("pms_packet_rule")
@Data
@EqualsAndHashCode(callSuper = false)
public class PacketRuleEntity extends FullAuditedEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * tenantId
     */
    @TableField(value = "tenantId")
    private Long tenantId;

    /**
     * 规则名称
     */
    @TableField(value = "packetName")
    private String packetName;

    /**
     * 包期类型，1：包月，2：包年
     */
    @TableField(value = "packetType")
    private Integer packetType;

    /**
     * 是否自然年月
     */
    @TableField(value = "naturalPeriod")
    private boolean naturalPeriod;

    /**
     * 车牌颜色
     */
    @TableField(value = "plateColor")
    private Integer plateColor;

    /**
     * 车牌类型
     */
    @TableField(value = "plateType")
    private String plateType;

    /**
     * 车辆类型
     */
    @TableField(value = "carType")
    private Integer carType;

    /**
     * 包期价格，单位：分
     */
    @TableField(value = "price")
    private Integer price;

    /**
     * 是否全部停车场：0 否；1 是
     */
    @TableField("allParking")
    private Integer allParking;

    /**
     * 创建者
     */
    @TableField(value = "creatorUserId", fill = FieldFill.INSERT)
    private Long creatorUserId;

    /**
     * 创建时间
     */
    @TableField(value = "creationTime", fill = FieldFill.INSERT)
    private Date creationTime;

    /**
     * 更新者
     */
    @TableField(value = "lastModifierUserId", fill = FieldFill.INSERT_UPDATE)
    private Long lastModifierUserId;

    /**
     * 更新时间
     */
    @TableField(value = "lastModificationTime", fill = FieldFill.INSERT_UPDATE)
    private Date lastModificationTime;

    /**
     * 删除者
     */
    @TableField(value = "deleterUserId", fill = FieldFill.UPDATE)
    private Long deleterUserId;

    /**
     * 删除时间
     */
    @TableField(value = "deletionTime", fill = FieldFill.UPDATE)
    private Date deletionTime;

    /**
     * 删除标记
     */
    @TableField(value = "deleted", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleted;

}
