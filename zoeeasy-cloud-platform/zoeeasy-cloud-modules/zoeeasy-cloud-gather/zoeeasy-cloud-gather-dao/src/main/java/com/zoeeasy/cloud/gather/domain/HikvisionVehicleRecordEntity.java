package com.zoeeasy.cloud.gather.domain;

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
 * 海康平台停车场过车记录
 *
 * @author walkman
 */
@TableName("gat_hikvision_vehicle_record")
@Data
@EqualsAndHashCode(callSuper = false)
public class HikvisionVehicleRecordEntity extends CreationAuditedEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 过车记录唯一编号
     */
    @TableField(value = "passingUuid")
    private String passingUuid;

    /**
     * 海康平台过车记录唯一ID
     */
    @TableField(value = "uuid")
    private String uuid;

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
     * 过车方向
     */
    @TableField(value = "passDirect")
    private Integer passDirect;

    /**
     * 通过时间
     */
    @TableField(value = "passTime")
    private Date passTime;

    /**
     * 停车场编号
     */
    @TableField(value = "parkCode")
    private String parkCode;

    /**
     * 停车场名称
     */
    @TableField(value = "parkName")
    private String parkName;

    /**
     * 停车场出入口编号
     */
    @TableField(value = "gateCode")
    private String gateCode;

    /**
     * 停车场出入口名称
     */
    @TableField(value = "gateName")
    private String gateName;

    /**
     * 车道编号
     */
    @TableField(value = "laneCode")
    private String laneCode;

    /**
     * 车道名称
     */
    @TableField(value = "laneName")
    private String laneName;

    /**
     * 泊位编号
     */
    @TableField(value = "berthCode")
    private String berthCode;

    /**
     * 泊位号
     */
    @TableField(value = "berthNumber")
    private String berthNumber;

    /**
     * 处理状态
     */
    @TableField(value = "processStatus")
    private Integer processStatus;

    /**
     * 处理说明
     */
    @TableField(value = "processRemark")
    private String processRemark;

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
