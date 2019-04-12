package com.zoeeasy.cloud.invoice.domain;

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
 * 发票订单表(inv_invoice_order)表实体类
 *
 * @author AkeemSuper
 * @date 2019-02-20 17:31:14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("inv_invoice_order")
public class InvoiceOrderEntity extends FullAuditedEntity<Long> implements Serializable {

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
     * 发票订单号
     */
    @TableField("orderNo")
    private String orderNo;

    /**
     * 申请订单号
     */
    @TableField("applyNo")
    private String applyNo;

    /**
     * 申请时间
     */
    @TableField("applyTime")
    private Date applyTime;

    /**
     * 发票类型 1:电子发票 2:纸质发票
     */
    @TableField("invoiceType")
    private Integer invoiceType;

    /**
     * 抬头类型 1:单位 2:个人
     */
    @TableField("headerType")
    private Integer headerType;

    /**
     * 发票内容类型 1:类别 2:明细
     */
    @TableField("contentType")
    private Integer contentType;

    /**
     * 发票内容
     */
    @TableField("content")
    private String content;

    /**
     * 购方名称,单位抬头为单位名称，个人抬头为个人
     */
    @TableField("buyerName")
    private String buyerName;

    /**
     * 购方税号
     */
    @TableField("taxNumber")
    private String taxNumber;

    /**
     * 银行账号
     */
    @TableField("account")
    private String account;

    /**
     * 购方电话
     */
    @TableField("telephone")
    private String telephone;

    /**
     * 购方地址
     */
    @TableField("address")
    private String address;

    /**
     * 开票金额(分)
     */
    @TableField("invoiceAmount")
    private Integer invoiceAmount;

    /**
     * 购方手机(开票成功会短信提醒购方)
     */
    @TableField("phoneNumber")
    private String phoneNumber;

    /**
     * 销方名称
     */
    @TableField("saleName")
    private String saleName;

    /**
     * 销方税号
     */
    @TableField("saleTaxNumber")
    private String saleTaxNumber;

    /**
     * 销方银行账号
     */
    @TableField("saleAccount")
    private String saleAccount;

    /**
     * 销方电话
     */
    @TableField("saleTelephone")
    private String saleTelephone;

    /**
     * 销方地址
     */
    @TableField("saleAddress")
    private String saleAddress;

    /**
     * 开票员
     */
    @TableField("clerk")
    private String clerk;

    /**
     * 收款人
     */
    @TableField("receiver")
    private String receiver;

    /**
     * 复核人
     */
    @TableField("checker")
    private String checker;

    /**
     * 收件人姓名
     */
    @TableField("receiverName")
    private String receiverName;

    /**
     * 省代码
     */
    @TableField("provinceCode")
    private String provinceCode;

    /**
     * 市代码
     */
    @TableField("cityCode")
    private String cityCode;

    /**
     * 区代码
     */
    @TableField("countyCode")
    private String countyCode;

    /**
     * 地址
     */
    @TableField("receiverAddress")
    private String receiverAddress;

    /**
     * 支付状态 0 :未支付 1:已支付 2:支付中
     */
    @TableField("payStatus")
    private Integer payStatus;

    /**
     * 支付金额(分)
     */
    @TableField("payedAmount")
    private Integer payedAmount;

    /**
     * 支付时间
     */
    @TableField("payTime")
    private Date payTime;

    /**
     * 支付方式(根据PayWayEnum)
     */
    @TableField("payWay")
    private Integer payWay;

    /**
     * 支付类型(根据PayTypeEnum)
     */
    @TableField("payType")
    private Integer payType;

    /**
     * 开票状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 完成时间
     */
    @TableField("completeTime")
    private Date completeTime;

    /**
     * 开票订单数
     */
    @TableField("orderCount")
    private Integer orderCount;

    /**
     * 审核用户ID
     */
    @TableField("auditUserId")
    private Long auditUserId;

    /**
     * 审核时间
     */
    @TableField("auditTime")
    private Date auditTime;

    /**
     * 审核备注
     */
    @TableField("auditRemark")
    private String auditRemark;

    /**
     * 运费支付方式 1 :用户支付 2:包邮 3:到付
     */
    @TableField("expressType")
    private Integer expressType;

    /**
     * 运费
     */
    @TableField("expressAmount")
    private Integer expressAmount;

    /**
     * 快递公司
     */
    @TableField("expressName")
    private String expressName;

    /**
     * 快递单号
     */
    @TableField("expressNo")
    private String expressNo;

    /**
     * 物流状态
     */
    @TableField("expressStatus")
    private Integer expressStatus;

    /**
     * 签收用户ID
     */
    @TableField("signUserId")
    private Long signUserId;

    /**
     * 签收时间
     */
    @TableField("signTime")
    private Date signTime;

    /**
     * 开票日期
     */
    @TableField("invoiceDate")
    private Date invoiceDate;

    /**
     * 发票代码
     */
    @TableField("invoiceCode")
    private String invoiceCode;

    /**
     * 发票号码
     */
    @TableField("invoiceNumber")
    private String invoiceNumber;

    /**
     * 校验码
     */
    @TableField("checkCode")
    private String checkCode;

    /**
     * 电子发票Url
     */
    @TableField("invoiceUrl")
    private String invoiceUrl;

    /**
     * 备注
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