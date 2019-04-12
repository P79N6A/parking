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
 * @Description:停车场出入口信息表
 * @Autor: Kane
 * @Date: 2018/9/14
 */
@TableName("pms_parking_gate_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingGateInfoEntity extends FullAuditedEntity<Long> implements Serializable {

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
     * 出入口编号
     */
    @TableField("code")
    private String code;

    /**
     * 客户端编号
     */
    @TableField("localCode")
    private String localCode;

    /**
     * 出入口名称
     */
    @TableField("name")
    private String name;

    /**
     * 出入口方向:
     * 1.入口
     * 2.出口
     * 3.出入口
     */
    @TableField("direction")
    private Integer direction;

    /**
     * 车道数量
     */
    @TableField("laneCount")
    private Integer laneCount;

    /**
     * 备注
     */
    @TableField("remarks")
    private String remarks;

    /**
     * 出入口经度
     */
    @TableField("longitude")
    private Double longitude;

    /**
     * 出入口纬度
     */
    @TableField("latitude")
    private Double latitude;

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
