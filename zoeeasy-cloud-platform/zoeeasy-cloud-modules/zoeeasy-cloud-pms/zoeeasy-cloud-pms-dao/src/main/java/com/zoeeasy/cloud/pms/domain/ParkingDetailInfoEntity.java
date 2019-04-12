package com.zoeeasy.cloud.pms.domain;

import com.baomidou.mybatisplus.annotations.*;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.IVersion;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Description:停车场详细信息表
 * @Autor: Kane
 * @Date: 2018/9/14
 */
@TableName(value = "pms_parking_detail_info")
@Data
@EqualsAndHashCode(callSuper = false)
public class ParkingDetailInfoEntity extends FullAuditedEntity<Long> implements IVersion<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    @TableField("tenantId")
    private Long tenantId;

    /**
     * 停车场ID
     */
    @TableField("parkingId")
    private Long parkingId;

    /**
     * 省编码
     */
    @TableField("provinceCode")
    private String provinceCode;

    /**
     * 市编码
     */
    @TableField("cityCode")
    private String cityCode;

    /**
     * 区县编码
     */
    @TableField("countyCode")
    private String countyCode;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 邮编
     */
    @TableField("zipCode")
    private String zipCode;

    /**
     * 管理单位
     */
    @TableField("managerUnit")
    private String managerUnit;

    /**
     * 管理人单位
     */
    @TableField("ownerName")
    private String ownerName;

    /**
     * 运营人单位
     */
    @TableField("operatorUnit")
    private String operatorUnit;

    /**
     * 收费单位
     */
    @TableField("chargerUnit")
    private String chargerUnit;

    /**
     * 联系人
     */
    @TableField("contactName")
    private String contactName;

    /**
     * 联系电话
     */
    @TableField("contactTel")
    private String contactTel;

    /**
     * 联系手机
     */
    @TableField("contactPhone")
    private String contactPhone;

    /**
     * 联系人邮箱
     */
    @TableField("contactEmail")
    private String contactEmail;

    /**
     * 联系人QQ
     */
    @TableField("contactQQ")
    private String contactQQ;

    /**
     * 联系人微信
     */
    @TableField("contactWeixin")
    private String contactWeixin;

    /**
     * 联系人支付宝
     */
    @TableField("contactAlipay")
    private String contactAlipay;

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

    /**
     * 版本号
     */
    @Version
    private Long version;

}
