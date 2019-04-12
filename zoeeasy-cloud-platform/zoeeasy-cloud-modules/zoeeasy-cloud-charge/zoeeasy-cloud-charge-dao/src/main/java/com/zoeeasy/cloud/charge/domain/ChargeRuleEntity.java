package com.zoeeasy.cloud.charge.domain;

import com.baomidou.mybatisplus.annotations.*;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.IVersion;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 收费规则实体
 *
 * @author AkeemSuper
 * @date 2018/9/13 0013
 */
@TableName("chg_charge_rule")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class ChargeRuleEntity extends FullAuditedEntity<Long> implements IVersion<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    @TableField(value = "tenantId")
    private Long tenantId;

    /**
     * 收费规则编号
     */
    @TableField(value = "code")
    private String code;

    /**
     * 收费规则名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 规则类型  1 不收费,2 按时计费 3,按次计费 4,日夜分时分次计费
     */
    @TableField(value = "ruleType")
    private Integer ruleType;

    /**
     * 车辆类型 1 大汽车 2小汽车 3其他
     */
    @TableField(value = "carType")
    private Integer carType;

    /**
     * 车牌类型
     */
    @TableField(value = "plateNumberType")
    private String plateNumberType;

    /**
     * 停车点等级 1,不区分 ,2一级, 3 二级, 4 三级
     */
    @TableField(value = "parkingLevel")
    private Integer parkingLevel;

    /**
     * 假期类型: 1工作日，2双休 3 小长假 4长假
     */
    @TableField(value = "holidayType")
    private Integer holidayType;

    /**
     * 计时开始时间
     */
    @TableField(value = "timesStartTime")
    private String timesStartTime;

    /**
     * 计时结束时间
     */
    @TableField(value = "timesEndTime")
    private String timesEndTime;

    /**
     * 计次开始时间
     */
    @TableField(value = "perStartTime")
    private String perStartTime;

    /**
     * 计次结束时间
     */
    @TableField(value = "perEndTime")
    private String perEndTime;

    /**
     * 按次收费金额
     */
    @TableField(value = "perPrice")
    private Integer perPrice;

    /**
     * 是否启用封顶金额：0,不启用1,启用
     */
    @TableField(value = "topStatus")
    private Boolean topStatus;

    /**
     * 免费时长(分钟)
     */
    @TableField(value = "freeTime")
    private Integer freeTime;

    /**
     * 超出免费时长时免费时长是否收费 0,不收费 1,收费
     */
    @TableField(value = "overFreeTime")
    private Boolean overFreeTime;

    /**
     * 24小时内存在按时段收费则全按时段收费 0,不存在 1,存在
     */
    @TableField(value = "existPartTime")
    private Boolean existPartTime;

    /**
     * 24小时内是否启用封顶金额 0,不启用1,启用
     */
    @TableField(value = "inTimeTop")
    private Boolean inTimeTop;

    /**
     * 24小时封顶金额(单位:分)
     */
    @TableField(value = "dayTopAmount")
    private Integer dayTopAmount;

    /**
     * 封顶金额(单位:分)
     */
    @TableField(value = "topAmount")
    private Integer topAmount;

    /**
     * 最小计时单位(单位:分钟)
     */
    @TableField(value = "unitTime")
    private Integer unitTime;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

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
