package com.zhuyitech.parking.data.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 云平台过车数据
 *
 * @author wangfeng
 * @since 2018/12/3 13:24
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class PassingVehicleRecordResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 平台过车流水号
     */
    private String passingNo;

    /**
     * 平台过车UUID
     */
    private String passingUuid;

    /**
     * 停车场ID
     */
    private Long parkingId;

    /**
     * 设备编号
     */
    private String machine;

    /**
     * 海康平台过车ID
     */
    private String hikPassingId;

    /**
     * 阿里平台过车ID
     */
    private String aliPassingId;

    /**
     * 泊位ID
     */
    private Long parkingLotId;

    /**
     * 车牌号
     */
    private String plateNumber;

    /**
     * 车牌颜色
     */
    private Integer plateColor;

    /**
     * 车辆类型
     */
    private Integer carType;

    /**
     * 校对状态
     */
    private Boolean proofStatus;

    /**
     * 校对用户
     */
    private Long proofUserId;

    /**
     * 校对用户
     */
    private Date proofDate;

    /**
     * 数据来源
     */
    private Integer dataSource;

    /**
     * 过车置信度
     */
    private Integer confidence;

    /**
     * 车牌号置信度
     */
    private Integer plateNumberConfidence;

    /**
     * 过车类型 1.入车 2.出车
     */
    private Integer passCarType;

    /**
     * 过车时间
     */
    private Date passTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 入车时间
     */
    private Date entryTime;

    /**
     * 出车时间
     */
    private Date exitTime;

    /**
     * 编辑人员
     */
    private Boolean photoUploaded;

    /**
     * 图片数量
     */
    private Integer photoCount;

    /**
     * 图片上传时间
     */
    private Date uploadedDate;

    /**
     * 创建者
     */
    private Long creatorUserId;

    /**
     * 创建时间
     */
    private Date creationTime;

    /**
     * 更新者
     */
    protected Long lastModifierUserId;

    /**
     * 更新日期
     */
    protected Date lastModificationTime;

    /**
     * 删除者
     */
    private Long deleterUserId;

    /**
     * 删除时间
     */
    private Date deletionTime;

    /**
     * 删除标记
     */
    private Integer deleted;

    private List<String> images;
}

