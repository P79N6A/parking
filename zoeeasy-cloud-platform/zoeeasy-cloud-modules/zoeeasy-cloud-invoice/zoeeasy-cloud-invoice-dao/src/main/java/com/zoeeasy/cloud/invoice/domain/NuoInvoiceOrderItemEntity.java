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
 * 诺诺发票订单明细(inv_nuo_invoice_order_item)表实体类
 *
 * @author AkeemSuper
 * @date 2019-02-19 15:30:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("inv_nuo_invoice_order_item")
public class NuoInvoiceOrderItemEntity extends FullAuditedEntity<Long> implements Serializable {

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
     * 商品名称
     */
    @TableField("goodsName")
    private String goodsName;

    /**
     * 数量
     */
    @TableField("num")
    private String num;

    /**
     * 单价
     */
    @TableField("price")
    private String price;

    /**
     * 单价含税标志 0:不含税,1:含税
     */
    @TableField("hsbz")
    private String hsbz;

    /**
     * 税率
     */
    @TableField("taxRate")
    private String taxRate;

    /**
     * 规格型号
     */
    @TableField("spec")
    private String spec;

    /**
     * 单位
     */
    @TableField("unit")
    private String unit;

    /**
     * 税收分类编码
     */
    @TableField("spbm")
    private String spbm;

    /**
     * 自行编码
     */
    @TableField("zsbm")
    private String zsbm;

    /**
     * 发票行性质: 0,正常行; 1,折扣行; 2,被折扣行
     */
    @TableField("fphxz")
    private String fphxz;

    /**
     * 优惠政策标识:0,不使用;1,使用
     */
    @TableField("yhzcbs")
    private String yhzcbs;

    /**
     * 增值税特殊管理
     */
    @TableField("zzstsgl")
    private String zzstsgl;

    /**
     * 零税率标识:空,非零税率;1,免税;2,不征税;3,普通零税率
     */
    @TableField("lslbs")
    private String lslbs;

    /**
     * 扣除额
     */
    @TableField("kce")
    private String kce;

    /**
     * 不含税金额
     */
    @TableField("taxFreeAmt")
    private String taxFreeAmt;

    /**
     * 税额
     */
    @TableField("tax")
    private String tax;

    /**
     * 含税金额
     */
    @TableField("taxAmt")
    private String taxAmt;

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