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
 * 支付宝异步通知
 *
 * @author zwq
 */
@TableName("pay_alipay_message_log")
@Data
@EqualsAndHashCode(callSuper = false)
public class AlipayMessageLogEntity extends CreationAuditedEntity<Long> implements Serializable {

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 通知时间
     */
    @TableField("notifyTime")
    private Date notifyTime;

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
     * 通知的类型
     */
    @TableField("notifyType")
    private String notifyType;

    /**
     * 通知校验ID
     */
    @TableField("notifyId")
    private String notifyId;

    /**
     * 支付宝分配给开发者的应用Id
     */
    @TableField("appId")
    private String appId;

    /**
     * 支付宝交易号
     */
    @TableField("tradeNo")
    private String tradeNo;

    /**
     * 商户订单号
     */
    @TableField("outTradeNo")
    private String outTradeNo;

    /**
     * 商户业务号
     */
    @TableField("outBizNo")
    private String outBizNo;

    /**
     * 消息内容
     */
    @TableField("content")
    private String content;

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
