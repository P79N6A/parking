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
 * 诺诺发票订单表(inv_nuo_invoice_order)表实体类
 *
 * @author AkeemSuper
 * @date 2019-02-20 17:31:16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("inv_nuo_invoice_order")
public class NuoInvoiceOrderEntity extends FullAuditedEntity<Long> implements Serializable {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单号
     */
    @TableField("orderNo")
    private String orderNo;

    /**
     * 发票请求流水号
     */
    @TableField("fpqqlsh")
    private String fpqqlsh;

    /**
     * 开票时间
     */
    @TableField("invoiceDate")
    private Date invoiceDate;

    /**
     * 发票主键
     */
    @TableField("invoiceId")
    private String invoiceId;

    /**
     * 诺诺网备案身份
     */
    @TableField("identity")
    private String identity;

    /**
     * 购方名称
     */
    @TableField("buyerName")
    private String buyerName;

    /**
     * 购方税号
     */
    @TableField("taxNum")
    private String taxNum;

    /**
     * 购方手机(开票成功会短信提醒购方)
     */
    @TableField("phone")
    private String phone;

    /**
     * 购方地址
     */
    @TableField("address")
    private String address;

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
     * 销方税号
     */
    @TableField("saleTaxNum")
    private String saleTaxNum;

    /**
     * 销方银行账号
     */
    @TableField("saleAccount")
    private String saleAccount;

    /**
     * 销方电话
     */
    @TableField("salePhone")
    private String salePhone;

    /**
     * 销方地址
     */
    @TableField("saleAddress")
    private String saleAddress;

    /**
     * 开票类型:1,正票;2, 红票
     */
    @TableField("kpType")
    private String kpType;

    /**
     * 备注信息
     */
    @TableField("message")
    private String message;

    /**
     * 收款人
     */
    @TableField("payee")
    private String payee;

    /**
     * 复核人
     */
    @TableField("checker")
    private String checker;

    /**
     * 开票员
     */
    @TableField("clerk")
    private String clerk;

    /**
     * 红票必填，不满12位请左补 0, 对应蓝票发票代码
     */
    @TableField("redFpdm")
    private String redFpdm;

    /**
     * 红票必填，不满8位请左补 0,对应蓝票发票号码
     */
    @TableField("redFphm")
    private String redFphm;

    /**
     * 推送方式 -1,不推送;0,邮箱;1,手机(默认);2,邮箱、手机
     */
    @TableField("tsfs")
    private String tsfs;

    /**
     * 推送邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 清单标志:0,根据项目名称数自动产生清单; 1, 将项目信息打印至清单
     */
    @TableField("qdbz")
    private String qdbz;

    /**
     * 清单项目名称
     */
    @TableField("qdxmmc")
    private String qdxmmc;

    /**
     * 代开标志:0非代开;1 代开
     */
    @TableField("dkbz")
    private String dkbz;

    /**
     * 部门门店
     */
    @TableField("deptId")
    private String deptId;

    /**
     * 开票员id
     */
    @TableField("clerkId")
    private String clerkId;

    /**
     * 发票种类
     */
    @TableField("invoiceLine")
    private String invoiceLine;

    /**
     * 成品油标志：0 非成品油， 1 成品油
     */
    @TableField("cpybz")
    private String cpybz;

    /**
     * 2 为开票并签章成功，其他状态分别为 20:未开票;21:提交服务器开票成功;22:提交服务器开票失败;24:签章失败;3 为发票已作废 31：发票作废中
     */
    @TableField("status")
    private String status;

    /**
     * 发票地址
     */
    @TableField("pdfUrl")
    private String pdfUrl;

    /**
     * 发票详情地址
     */
    @TableField("jpgUrl")
    private String jpgUrl;

    /**
     * 开票日期
     */
    @TableField("kprq")
    private Date kprq;

    /**
     * 发票代码
     */
    @TableField("fpdm")
    private String fpdm;

    /**
     * 发票号码
     */
    @TableField("fphm")
    private String fphm;

    /**
     * 校验码
     */
    @TableField("jym")
    private String jym;

    /**
     * 不含税金额
     */
    @TableField("bhsje")
    private String bhsje;

    /**
     * 不含税金额
     */
    @TableField("hjse")
    private String hjse;

    /**
     * 开票信息，成功或者失败的信息
     */
    @TableField("result")
    private String result;

    /**
     * 结果信息
     */
    @TableField("resultMessage")
    private String resultMessage;

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