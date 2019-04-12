package com.zoeeasy.cloud.message.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author walkman
 */
@TableName("msg_message")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class MessageEntity extends FullAuditedEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 消息ID
     */
    @TableField("messageId")
    private String messageId;

    /**
     * traceId
     */
    @TableField("traceId")
    private String traceId;

    /**
     * 消息发送方
     */
    @TableField("sender")
    private String sender;

    /**
     * 消息交换器
     */
    @TableField("exchangeTopic")
    private String exchangeTopic;

    /**
     * 路由键
     */
    @TableField("routingKey")
    private String routingKey;

    /**
     * 消息队列
     */
    @TableField("consumerQueue")
    private String consumerQueue;

    /**
     * 消息内容
     */
    @TableField("payload")
    private String payload;

    /**
     * 消息类型
     */
    @TableField("messageType")
    private String messageType;

    /**
     * 发送次数
     */
    @TableField("sendTimes")
    private Integer sendTimes;

    /**
     * 发送状态
     */
    @TableField("sendStatus")
    private Integer sendStatus;

    /**
     * 发送结果
     */
    @TableField("sendResult")
    private String sendResult;

    /**
     * 是否死信
     */
    @TableField("alreadyDead")
    private Integer alreadyDead;

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
