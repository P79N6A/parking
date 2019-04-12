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
 * Created by song on 2019/3/22 20:36
 */
@TableName("pms_parking_floor")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingFloorEntity extends FullAuditedEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户Id
     */
    @TableField("tenantId")
    private Long tenantId;

    /**
     * 停车场id
     */
    @TableField("parkingId")
    private Long parkingId;

    /**
     * 车库id
     */
    @TableField("garageId")
    private Long garageId;

    /**
     * 楼层code
     */
    @TableField("floorCode")
    private String floorCode;

    /**
     * 楼层name
     */
    @TableField("floorName")
    private String floorName;

    /**
     * 泊位数量
     */
    @TableField("lotCount")
    private Integer lotCount;

    /**
     * 固定车位数
     */
    @TableField("lotFixed")
    private Integer lotFixed;

    /**
     * 剩余车位数
     */
    @TableField("lotAvailable")
    private Integer lotAvailable;

    /**
     * 楼层说明
     */
    @TableField("remark")
    private String remark;

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
