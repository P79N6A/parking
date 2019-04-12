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
 * 停车场审核
 */
@TableName("pms_parking_apply_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingApplyInfoEntity extends FullAuditedEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户id
     */
    @TableField("tenantId")
    private Long tenantId;

    /**
     * 停车场id
     */
    @TableField("parkingId")
    private Long parkingId;

    /**
     * 申请类别: 1上线申请 2下线申请
     */
    @TableField("applyType")
    private Integer applyType;

    /**
     * 申请状态: 1待申请 2已申请 3已取消
     */
    @TableField("applyStatus")
    private Integer applyStatus;

    /**
     * 申请人id
     */
    @TableField("applyUserId")
    private Long applyUserId;

    /**
     * 申请说明
     */
    @TableField("applyRemark")
    private String applyRemark;

    /**
     * 申请时间
     */
    @TableField("applyTime")
    private Date applyTime;

    /**
     * 审核状态: 1待审核 2通过 3未通过
     */
    @TableField("auditStatus")
    private Integer auditStatus;

    /**
     * 审核人员id
     */
    @TableField("auditUserId")
    private Long auditUserId;

    /**
     * 审核不通过原因选择
     */
    @TableField("auditNotPassReason")
    private Integer auditNotPassReason;

    /**
     * 审核说明
     */
    @TableField("auditRemark")
    private String auditRemark;

    /**
     * 审核时间
     */
    @TableField("auditTime")
    private Date auditTime;

    /**
     * 上下线状态: 1待上线 2已上线 3待下线 4已下线
     */
    @TableField("runStatus")
    private Integer runStatus;

    /**
     * 上下线操作人员
     */
    @TableField("runUserId")
    private Long runUserId;

    /**
     * 上下线操作时间
     */
    @TableField("runOperateTime")
    private Date runOperateTime;

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
