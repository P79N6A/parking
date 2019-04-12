package com.zhuyitech.parking.pms.domain;

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
 * 车型大全实体对象
 *
 * @author walkman
 */
@TableName("up_car_brand")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class CarBrand extends FullAuditedEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 全称
     */
    @TableField(value = "fullName")
    private String fullName;

    /**
     * 首字母
     */
    @TableField(value = "initial")
    private String initial;

    /**
     * 父ID
     */
    @TableField(value = "parentId")
    private Long parentId;

    /**
     *
     */
    @TableField(value = "parentIds")
    private String parentIds;

    /**
     * 图标
     */
    @TableField(value = "logo")
    private String logo;

    /**
     * 深度 1品牌 2子公司 3车型 4具体车型
     */
    @TableField(value = "depth")
    private Integer depth;

    /**
     * 价格字符
     */
    @TableField(value = "price")
    private String price;

    /**
     * 年款
     */
    @TableField(value = "yearType")
    private String yearType;

    /**
     * 车辆等级
     */
    @TableField(value = "sizeType")
    private String sizeType;

    /**
     * 销售状态
     */
    @TableField(value = "saleState")
    private String saleState;

    /**
     * 生产状态
     */
    @TableField(value = "productionState")
    private String productionState;

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

}
