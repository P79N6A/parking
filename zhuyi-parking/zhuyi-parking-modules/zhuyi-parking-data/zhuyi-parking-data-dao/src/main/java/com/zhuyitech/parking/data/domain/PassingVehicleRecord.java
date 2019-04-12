package com.zhuyitech.parking.data.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.auditing.FullAuditedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 平台过车记录
 *
 * @Date: 2018/1/11 0011
 * @author: AkeemSuper
 */
@TableName("up_passing_vehicle_record")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class PassingVehicleRecord extends FullAuditedEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 平台过车流水号
     */
    @TableField(value = "passingNo")
    private String passingNo;

    /**
     * 平台过车UUID
     */
    @TableField(value = "passingUuid")
    private String passingUuid;

    /**
     * 停车场ID
     */
    @TableField(value = "parkingId")
    private Long parkingId;

    /**
     * 设备编号
     */
    @TableField(value = "machine")
    private String machine;

    /**
     * 海康平台过车ID
     */
    @TableField(value = "hikPassingId")
    private String hikPassingId;

    /**
     * 阿里平台过车ID
     */
    @TableField(value = "aliPassingId")
    private String aliPassingId;

    /**
     * 泊位ID
     */
    @TableField(value = "parkingLotId")
    private Long parkingLotId;

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
     * 校对状态
     */
    @TableField(value = "proofStatus")
    private Boolean proofStatus;

    /**
     * 校对用户
     */
    @TableField(value = "proofUserId")
    private Long proofUserId;

    /**
     * 校对用户
     */
    @TableField(value = "proofDate")
    private Date proofDate;

    /**
     * 数据来源
     */
    @TableField(value = "dataSource")
    private Integer dataSource;

    /**
     * 过车置信度
     */
    @TableField(value = "confidence")
    private Integer confidence;

    /**
     * 车牌号置信度
     */
    @TableField(value = "plateNumberConfidence")
    private Integer plateNumberConfidence;

    /**
     * 过车类型 1.入车 2.出车
     */
    @TableField(value = "passCarType")
    private Integer passCarType;

    /**
     * 过车时间
     */
    @TableField(value = "passTime")
    private Date passTime;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 入车时间
     */
    @TableField(value = "entryTime")
    private Date entryTime;

    /**
     * 出车时间
     */
    @TableField(value = "exitTime")
    private Date exitTime;

    /**
     * 编辑人员
     */
    @TableField(value = "photoUploaded")
    private Boolean photoUploaded;

    /**
     * 图片数量
     */
    @TableField(value = "photoCount")
    private Integer photoCount;

    /**
     * 图片上传时间
     */
    @TableField(value = "uploadedDate")
    private Date uploadedDate;

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
    private Long deleterUserId;

    /**
     * 删除时间
     */
    @TableField(value = "deletionTime", fill = FieldFill.UPDATE)
    private Date deletionTime;

    /**
     * 删除标记
     */
    @TableField(value = "deleted", fill = FieldFill.INSERT)
    private Integer deleted;

}
