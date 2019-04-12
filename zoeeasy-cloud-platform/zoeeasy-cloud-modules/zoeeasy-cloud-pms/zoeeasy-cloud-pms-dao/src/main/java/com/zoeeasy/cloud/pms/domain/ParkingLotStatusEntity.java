package com.zoeeasy.cloud.pms.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.auditing.CreationAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 停车场泊位当前状态信息
 * Created by song on 2018/9/14.
 */
@TableName("pms_parking_lot_status")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingLotStatusEntity extends CreationAuditedEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;
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
     * 泊位ID
     */
    @TableField("parkingLotId")
    private Long parkingLotId;
    /**
     * 泊位状态(0-空闲, 1-占用，2-未知)
     */
    @TableField("status")
    private Integer status;
    /**
     * 入车过车记录ID
     */
    @TableField("intoPassingId")
    private Long intoPassingId;
    /**
     * 车牌号码
     */
    @TableField("plateNumber")
    private String plateNumber;
    /**
     * 车牌颜色
     */
    @TableField("plateColor")
    private Integer plateColor;
    /**
     * 占用时间：泊位状态从0，2 到1 的时设置，1到1 不更新，1到0 更新成null
     */
    @TableField("occupyTime")
    private Date occupyTime;

}
