package com.zoeeasy.cloud.inspect.domain;

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
 * @author AkeemSuper
 * @date 2018/9/20 0020
 */
@TableName("spe_inspect_record")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class InspectRecordEntity extends CreationAuditedEntity<Long> implements Serializable {

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
     * 停车记录ID
     */
    @TableField(value = "recordId")
    private Long recordId;

    /**
     * 停车记录流水号
     */
    @TableField(value = "recordNo")
    private String recordNo;

    /**
     * 停车场ID
     */
    @TableField(value = "parkingId")
    private Long parkingId;

    /**
     * 泊位ID
     */
    @TableField(value = "parkingLotId")
    private Long parkingLotId;

    /**
     * 停车卡号
     */
    @TableField(value = "cardNumber")
    private String cardNumber;

    /**
     * 停车码
     */
    @TableField(value = "codeNumber")
    private String codeNumber;

    /**
     * 车牌号
     */
    @TableField(value = "plateNumber")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @TableField(value = "plateColor")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @TableField(value = "carType")
    private Integer carType;

    /**
     * 停车类型
     */
    @TableField(value = "parkingType")
    private Integer parkingType;

    /**
     * 停车开始时间
     */
    @TableField(value = "startTime")
    private Date startTime;

    /**
     * 停车结束时间
     */
    @TableField(value = "endTime")
    private Date endTime;

    /**
     * 状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 巡检员收费员ID
     */
    @TableField(value = "inspectUserId")
    private Long inspectUserId;

    /**
     * 巡查时间
     */
    @TableField(value = "inspectTime")
    private Date inspectTime;

    /**
     * 异常原因，1 -剩余车位数不正确，2 -车牌号码不匹配 3 车牌颜色不匹配 4 泊位不匹配 5 车型不匹配 6 入场时间不匹配
     */
    @TableField(value = "inspectReason")
    private String inspectReason;

    /**
     * 巡查结果，1 已处理 2 未处理
     */
    @TableField(value = "inspectResult")
    private Integer inspectResult;

    /**
     * 描述信息
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
}
