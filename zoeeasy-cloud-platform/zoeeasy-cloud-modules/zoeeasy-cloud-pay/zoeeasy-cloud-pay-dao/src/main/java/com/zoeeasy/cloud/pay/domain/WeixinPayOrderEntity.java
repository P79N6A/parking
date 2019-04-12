package com.zoeeasy.cloud.pay.domain;

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
 * 微信支付订单
 *
 * @author zwq
 */
@TableName("pay_wxpay_order")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class WeixinPayOrderEntity extends FullAuditedEntity<Long> implements Serializable, IVersion<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * tenantId
     */
    @TableId(value = "tenantId")
    private Long tenantId;

    /**
     * 车主用户ID
     */
    @TableField(value = "customerUserId")
    private Long customerUserId;

    /**
     * 商户订单号
     */
    @TableField(value = "outTradeNo")
    private String outTradeNo;

    /**
     * 微信支付订单号
     */
    @TableField(value = "transactionId")
    private String transactionId;

    /**
     * 预支付交易会话标识
     */
    @TableField(value = "prepayId")
    private String prepayId;

    /**
     * 二维码支付URL
     */
    @TableField(value = "qrCodeUrl")
    private String qrCodeUrl;

    /**
     * 二维码支付短连接URL
     */
    @TableField(value = "shortQrCodeUrl")
    private String shortQrCodeUrl;

    /**
     * 交易状态
     */
    @TableField(value = "tradeState")
    private Integer tradeState;

    /**
     * 交易状态描述
     */
    @TableField(value = "tradeStateDesc")
    private String tradeStateDesc;

    /**
     * 商品描述
     */
    @TableField(value = "body")
    private String body;

    /**
     * 商品详情
     */
    @TableField(value = "detail")
    private String detail;

    /**
     * 附加数据
     */
    @TableField(value = "attach")
    private String attach;

    /**
     * 交易类型
     */
    @TableField(value = "tradeType")
    private String tradeType;

    /**
     * 指定支付方式
     */
    @TableField(value = "limitPay")
    private String limitPay;

    /**
     * 场景信息
     */
    @TableField(value = "sceneInfo")
    private String sceneInfo;

    /**
     * 设备号
     */
    @TableField(value = "deviceInfo")
    private String deviceInfo;

    /**
     * 货币类型
     */
    @TableField(value = "feeType")
    private String feeType;

    /**
     * 总金额
     */
    @TableField(value = "totalFee")
    private Integer totalFee;

    /**
     * 现金支付金额
     */
    @TableField(value = "cashFee")
    private Integer cashFee;

    /**
     * 现金支付货币类型
     */
    @TableField(value = "cashFeeType")
    private String cashFeeType;

    /**
     * 代金券使用数量
     */
    @TableField(value = "couponFee")
    private Integer couponFee;

    /**
     * 应结订单金额
     */
    @TableField(value = "settlementTotalFee")
    private Integer settlementTotalFee;

    /**
     * 终端IP
     */
    @TableField(value = "spbillCreateIp")
    private String spbillCreateIp;

    /**
     * 交易起始时间
     */
    @TableField(value = "timeStart")
    private Date timeStart;

    /**
     * 支付完成时间
     */
    @TableField(value = "timeEnd")
    private Date timeEnd;

    /**
     * 订单优惠标记
     */
    @TableField(value = "goodsTag")
    private String goodsTag;

    /**
     * 用户标识
     */
    @TableField(value = "openid")
    private String openid;

    /**
     * 是否关注公众账号
     */
    @TableField(value = "subscribed")
    private Boolean subscribed;

    /**
     * 付款银行
     */
    @TableField(value = "bankType")
    private String bankType;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

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