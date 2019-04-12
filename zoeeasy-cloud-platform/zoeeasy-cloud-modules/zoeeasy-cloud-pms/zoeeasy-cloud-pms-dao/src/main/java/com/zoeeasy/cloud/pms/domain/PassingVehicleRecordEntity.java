package com.zoeeasy.cloud.pms.domain;

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
 * @author AkeemSuper
 * @date 2018/9/12 0012
 */
@TableName("pms_passing_vehicle_record")
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class PassingVehicleRecordEntity extends FullAuditedEntity<Long> implements Serializable {

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
     * 停车场ID
     */
    @TableField(value = "parkingId")
    private Long parkingId;

    /**
     * 停车场编号
     */
    @TableField(value = "parkingCode")
    private String parkingCode;

    /**
     * 停车场名称
     */
    @TableField(value = "parkingName")
    private String parkingName;

    /**
     * 车库ID
     */
    @TableField(value = "garageId")
    private Long garageId;

    /**
     * 出入口ID
     */
    @TableField(value = "gateId")
    private Long gateId;

    /**
     * 车道ID
     */
    @TableField(value = "laneId")
    private Long laneId;

    /**
     * 泊位ID
     */
    @TableField(value = "parkingLotId")
    private Long parkingLotId;

    /**
     * 泊位号
     */
    @TableField(value = "parkingLotNumber")
    private String parkingLotNumber;

    /**
     * 平台过车流水号
     */
    @TableField(value = "passingNo")
    private String passingNo;

    /**
     * 平台过车唯一编号
     */
    @TableField(value = "passingUuid")
    private String passingUuid;

    /**
     * 海康平台过车ID
     */
    @TableField(value = "thirdPassingId")
    private String thirdPassingId;

    /**
     * 阿里平台过车ID
     */
    @TableField(value = "aliPassingId")
    private String aliPassingId;

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
     * 车牌号是否存在
     */
    @TableField(value = "plateNoExist")
    private Boolean plateNoExist;

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
     * 车牌号置信度
     */
    @TableField(value = "plateNumberConfidence")
    private Integer plateNumberConfidence;

    /**
     * 过车类型 1.入车 2.出车
     */
    @TableField(value = "passingType")
    private Integer passingType;

    /**
     * 过车置信度
     */
    @TableField(value = "confidence")
    private Integer confidence;

    /**
     * 停车类型
     */
    @TableField(value = "parkingType")
    private Integer parkingType;

    /**
     * 过车时间
     */
    @TableField(value = "passTime")
    private Date passTime;

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
     * 数据来源
     */
    @TableField(value = "dataSource")
    private Integer dataSource;

    /**
     * 数据类型
     */
    @TableField(value = "dataType")
    private Integer dataType;

    /**
     * 过车图片是否上传
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
     * 异常过车类型：0 非异常；2 停车场存在
     */
    @TableField(value = "abnormalType")
    private Integer abnormalType;

    /**
     * 异常原因
     */
    @TableField(value = "abnormalReason")
    private String abnormalReason;

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
     * 校对时间
     */
    @TableField(value = "proofDate")
    private Date proofDate;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

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
    @TableLogic
    private Integer deleted;

}
