package com.zoeeasy.cloud.notify.domain;

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
 * 消息通知
 *
 * @author walkman
 * @date 2018/05/24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("not_notification")
@SuppressWarnings("serial")
public class Notification extends FullAuditedEntity<Long> implements Serializable, IVersion<Long> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    @TableField(value = "tenantId")
    private Long tenantId;

    /**
     * 模板ID
     */
    @TableField(value = "templateId")
    private Long templateId;

    /**
     * 用户编号
     */
    @TableField(value = "userId")
    private Long userId;

    /**
     * 消息通知类型(1:通知 2:活动 3:互动 4:快报)
     */
    @TableField(value = "notifyType")
    private Integer notifyType;

    /**
     * 业务类型
     */
    @TableField(value = "bizType")
    private Integer bizType;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 消息发送时间
     */
    @TableField(value = "sendTime")
    private Date sendTime;

    /**
     * 是否发送邮件：-1不发送 0 待发送，1已发送
     */
    @TableField("emailStatus")
    private Integer emailStatus;

    /**
     * 发送邮件时间
     */
    @TableField(value = "emailSendTime")
    private Date emailSendTime;

    /**
     * 是否发送短信：-1不发送 0 待发送，1已发送
     */
    @TableField("smsStatus")
    private Integer smsStatus;

    /**
     * 短信发送时间
     */
    @TableField(value = "smsSendTime")
    private Date smsSendTime;

    /**
     * 读标志：N未读，Y已读
     */
    @TableField("readStatus")
    private Boolean readStatus;

    /**
     * 阅读时间
     */
    @TableField(value = "readTime")
    private Date readTime;

    /**
     * 创建者
     */
    @TableField(value = "creatorUserId", fill = FieldFill.INSERT)
    private Long creatorUserId;

    /**
     * 创建日期
     */
    @TableField(value = "creationTime", fill = FieldFill.INSERT)
    private Date creationTime;

    /**
     * 更新者
     */
    @TableField(value = "lastModifierUserId", fill = FieldFill.INSERT_UPDATE)
    private Long lastModifierUserId;

    /**
     * 更新日期
     */
    @TableField(value = "lastModificationTime", fill = FieldFill.INSERT_UPDATE)
    private Date lastModificationTime;

    /**
     * 删除者
     */
    @TableField(value = "deleterUserId", fill = FieldFill.UPDATE)
    private Long deleterUserId;

    /**
     * 删除日期
     */
    @TableField(value = "deletionTime", fill = FieldFill.UPDATE)
    private Date deletionTime;

    /**
     * 删除标记（0：正常；1：删除 ）
     */
    @TableField(value = "deleted", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleted;

    /**
     * 版本号
     */
    @Version
    private Long version;

}
