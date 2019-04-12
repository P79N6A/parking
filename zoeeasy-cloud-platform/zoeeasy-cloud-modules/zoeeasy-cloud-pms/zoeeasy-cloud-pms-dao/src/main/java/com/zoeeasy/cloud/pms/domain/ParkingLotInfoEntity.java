package com.zoeeasy.cloud.pms.domain;

import com.baomidou.mybatisplus.annotations.*;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.IVersion;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 停车场泊位信息
 * Created by song on 2018/9/17.
 */
@TableName("pms_parking_lot_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingLotInfoEntity extends FullAuditedEntity<Long> implements IVersion<Long> {

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
     * 停车场ID
     */
    @TableField("parkingId")
    private Long parkingId;
    /**
     * 车库ID
     */
    @TableField(value = "garageId", fill = FieldFill.UPDATE)
    private Long garageId;
    /**
     * 泊车区域ID
     */
    @TableField(value = "parkingAreaId", fill = FieldFill.UPDATE)
    private Long parkingAreaId;

    /**
     * 楼层id
     */
    @TableField(value = "floorId")
    private Long floorId;

    /**
     * 海康平台泊位ID
     */
    @TableField("hikParkingLotId")
    private String hikParkingLotId;
    /**
     * 海康平台泊位号(停车场唯一)
     */
    @TableField("hikBerthNumber")
    private String hikBerthNumber;
    /**
     * 支付宝平台泊位ID
     */
    @TableField("aliParkingLotId")
    private String aliParkingLotId;
    /**
     * 编号(平台唯一)
     */
    @TableField("code")
    private String code;
    /**
     * 客户端编号
     */
    @TableField("localCode")
    private String localCode;
    /**
     * 泊位编号：停车场内唯一
     */
    @TableField("number")
    private String number;
    /**
     * 简称
     */
    @TableField("name")
    private String name;
    /**
     * 车位状态
     */
    @TableField("status")
    private Integer status;
    /**
     * 描述
     */
    @TableField("description")
    private String description;
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
