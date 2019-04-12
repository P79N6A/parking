package com.zoeeasy.cloud.pds.domain;

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
 * 地磁误报处理记录
 *
 * @Date: 2018/10/18
 * @author: lhj
 */
@TableName("pds_magnetic_error_report")
@Data
@EqualsAndHashCode(callSuper = false)
public class MagneticErrorReportEntity extends CreationAuditedEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户ID
     */
    @TableField(value = "tenantId")
    private Long tenantId;

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
     * 检测器ID
     */
    @TableField(value = "detectorId")
    private Long detectorId;

    /**
     * 地磁管理器类型(厂商) 数据字典
     */
    @TableField(value = "provider")
    private Integer provider;

    /**
     * (厂商)设备序列号
     */
    @TableField(value = "serialNumber")
    private String serialNumber;

    /**
     * 手持终端编号
     */
    @TableField(value = "terminalId")
    private String terminalId;

    /**
     * 地磁报告时间
     */
    @TableField(value = "reportTime")
    private Date reportTime;

    /**
     * 地磁报告类型(1-车辆到达, 2-车辆离开)
     */
    @TableField(value = "reportType")
    private Integer reportType;

    /**
     * 收费员或巡检员ID
     */
    @TableField(value = "inspectUserId")
    private Long inspectUserId;

    /**
     * 收费员或巡检员姓名
     */
    @TableField(value = "inspectUserName")
    private String inspectUserName;

    /**
     * 误报处理时间
     */
    @TableField(value = "processTime")
    private Date processTime;

    /**
     * 误报原因，0-地磁误报进车（进车时），1-地磁误报出车（出车时），2-连续上报
     */
    @TableField(value = "processReason")
    private Integer processReason;

    /**
     * 创建者
     */
    @TableField(value = "creatorUserId", fill = FieldFill.INSERT)
    private Long creatorUserId;

    /**
     * 创建时间
     */
    @TableField(value = "creationTime", fill = FieldFill.INSERT)
    private Date creationTime;
}


