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
 * 停车场车道信息
 * Created by song on 2018/9/14.
 */
@TableName("pms_parking_lane_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingLaneInfoEntity extends FullAuditedEntity<Long> implements IVersion<Long> {

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
     * 停车场ID
     */
    @TableField("parkingId")
    private Long parkingId;

    /**
     * 车库ID
     */
    @TableField("garageId")
    private Long garageId;

    /**
     * 出入口ID
     */
    @TableField(value = "gateId", fill = FieldFill.UPDATE)
    private Long gateId;

    /**
     * 车道序号
     */
    @TableField("code")
    private String code;

    /**
     * 客户端编号
     */
    @TableField("localCode")
    private String localCode;

    /**
     * 车道名称
     */
    @TableField("name")
    private String name;

    /**
     * 出入口方向：，1-入车道，2-出车道 3 出入车道
     */
    @TableField("direction")
    private Integer direction;

    /**
     * 备注
     */
    @TableField("remarks")
    private String remarks;

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
