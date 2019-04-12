package com.zhuyitech.parking.data.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 云平台停车记录数据
 *
 * @author wangfeng
 * @since 2018/12/3 13:29
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class ParkingRecordResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 停车记录流水号
     */
    private String recordNo;

    /**
     * 海康平台停车记录ID
     */
    private String thirdParkingRecordId;

    /**
     * 阿里平台停车记录ID
     */
    private String aliParkingRecordId;

    /**
     * 停车场ID
     */
    private Long parkingId;


    /**
     * 停车场编号
     */
    private String parkingCode;

    /**
     * 停车场名称
     */
    private String parkingName;

    /**
     * 泊位ID
     */
    private Long parkingLotId;

    /**
     * 泊位code
     */
    private String parkingLotCode;

    /**
     * 泊位code
     */
    private String parkingLotNumber;

    /**
     * 入车记录流水
     */
    private String intoRecordNo;

    /**
     * 入车记录流水
     */
    private String outRecordNo;


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
     * 停车开始时间
     */
    private Date startTime;

    /**
     * 停车结束时间
     */
    private Date endTime;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 是否预约停车
     */
    private Boolean appointed;

    /**
     * 预约订单号
     */
    private String appointOrderNo;

    /**
     * 创建者
     */
    private Long creatorUserId;

    /**
     * 创建日期
     */
    private Date creationTime;

    /**
     * 更新者
     */
    private Long lastModifierUserId;

    /**
     * 更新日期
     */
    private Date lastModificationTime;

    /**
     * 删除者
     */
    private Long deleterUserId;

    /**
     * 删除日期
     */
    private Date deletionTime;

    /**
     * 删除标记（0：正常；1：删除 ）
     */
    private Integer deleted;

    /**
     * 版本号
     */
    private Long version;

}

