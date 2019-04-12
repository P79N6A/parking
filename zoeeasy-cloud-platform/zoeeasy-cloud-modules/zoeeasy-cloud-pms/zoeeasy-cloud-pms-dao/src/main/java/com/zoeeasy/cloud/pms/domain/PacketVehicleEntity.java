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
 * 包期车辆实体
 *
 * @date: 2018/10/13.
 * @author：zm
 */
@TableName("pms_packet_vehicle")
@Data
@EqualsAndHashCode(callSuper = false)
public class PacketVehicleEntity extends FullAuditedEntity<Long> implements Serializable {

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
     * 包期规则ID
     */
    @TableField(value = "ruleId")
    private Long ruleId;

    /**
     * 车牌编号
     */
    @TableField(value = "plateNumber")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @TableField(value = "plateColor")
    private Integer plateColor;

    /**
     * 车牌类型
     */
    @TableField(value = "plateType")
    private String plateType;

    /**
     * 车辆类型
     */
    @TableField(value = "carType")
    private Integer carType;

    /**
     * 车辆颜色
     */
    @TableField(value = "carColor")
    private Integer carColor;

    /**
     * 开始日期
     */
    @TableField(value = "beginDate")
    private Date beginDate;

    /**
     * 结束日期
     */
    @TableField(value = "endDate")
    private Date endDate;

    /**
     * 是否有效：1 未生效；2 已生效 ；3 已失效
     */
    @TableField(value = "effectedStatus")
    private Integer effectedStatus;

    /**
     * 是否充值：0 未充值；1 已充值
     */
    @TableField(value = "topUpStatus")
    private Integer topUpStatus;

    /**
     * 是否全部停车场：0 否；1 是
     */
    @TableField("allParking")
    private Integer allParking;

    /**
     * 车主用户ID
     */
    @TableField(value = "customerUserId")
    private Long customerUserId;

    /**
     * 车主姓名
     */
    @TableField(value = "ownerName")
    private String ownerName;

    /**
     * 车主手机
     */
    @TableField(value = "ownerPhone")
    private String ownerPhone;

    /**
     * 车主邮件
     */
    @TableField(value = "ownerEmail")
    private String ownerEmail;

    /**
     * 车主地址
     */
    @TableField(value = "ownerAddress")
    private String ownerAddress;

    /**
     * 车主身份证号
     */
    @TableField(value = "ownerCardNo")
    private String ownerCardNo;

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
    private Long lastModifierUserId;

    /**
     * 更新时间
     */
    @TableField(value = "lastModificationTime", fill = FieldFill.INSERT_UPDATE)
    private Date lastModificationTime;

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
