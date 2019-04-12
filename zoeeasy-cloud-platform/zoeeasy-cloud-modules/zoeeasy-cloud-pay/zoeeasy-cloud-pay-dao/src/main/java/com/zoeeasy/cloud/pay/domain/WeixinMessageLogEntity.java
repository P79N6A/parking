package com.zoeeasy.cloud.pay.domain;

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
 * 微信异步通知
 *
 * @author zwq
 */
@TableName("pay_wxpay_message_log")
@Data
@EqualsAndHashCode(callSuper = false)
public class WeixinMessageLogEntity extends CreationAuditedEntity<Long> implements Serializable {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 通知时间
     */
    @TableField("messageType")
    private Integer messageType;

    /**
     *
     */
    @TableField("ip")
    private String ip;

    /**
     * url
     */
    @TableField("url")
    private String url;

    /**
     * outRefundNo
     */
    @TableField("outRefundNo")
    private String outRefundNo;

    /**
     * transactionId
     */
    @TableField("transactionId")
    private String transactionId;

    /**
     * refundId
     */
    @TableField("refundId")
    private String refundId;

    /**
     * 消息内容
     */
    @TableField("content")
    private String content;

    /**
     * 商户订单号
     */
    @TableField("outTradeNo")
    private String outTradeNo;

    /**
     * 消息处理状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 消息处理结果说明
     */
    @TableField("result")
    private String result;

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
}
