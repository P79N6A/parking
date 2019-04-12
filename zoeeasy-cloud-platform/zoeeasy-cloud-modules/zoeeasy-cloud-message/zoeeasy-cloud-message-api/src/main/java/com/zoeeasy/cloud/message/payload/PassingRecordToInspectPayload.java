package com.zoeeasy.cloud.message.payload;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 过车记录推送巡检消息体
 *
 * @author AkeemSuper
 * @date 2018/10/17 0017
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PassingRecordToInspectPayload implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "parkingId")
    private Long parkingId;

    /**
     * 出入口ID
     */
    @ApiModelProperty(value = "gateId")
    private Long gateId;

    /**
     * 泊位ID
     */
    @ApiModelProperty(value = "parkingLotId")
    private Long parkingLotId;

    /**
     * 泊位编号
     */
    @ApiModelProperty(value = "泊位编号")
    private String parkingLotCode;

    /**
     * 泊位Number
     */
    @ApiModelProperty(value = "泊位Number")
    private String parkingLotNumber;

    /**
     * 平台过车流水号
     */
    @ApiModelProperty(value = "passingNo")
    private String passingNo;

    /**
     * 平台过车唯一编号
     */
    @ApiModelProperty(value = "passingUuid")
    private String passingUuid;

    /**
     * 海康平台过车ID
     */
    @ApiModelProperty(value = "thirdPassingId")
    private String hikPassingId;

    /**
     * 阿里平台过车ID
     */
    @ApiModelProperty(value = "aliPassingId")
    private String aliPassingId;

    /**
     * 停车卡号
     */
    @ApiModelProperty(value = "cardNumber")
    private String cardNumber;

    /**
     * 停车码
     */
    @ApiModelProperty(value = "codeNumber")
    private String codeNumber;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "plateNumber")
    private String plateNumber;

    /**
     * 车牌号是否存在
     */
    @ApiModelProperty(value = "plateNoExist")
    private Boolean plateNoExist;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "plateColor")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "carType")
    private Integer carType;

    /**
     * 过车类型 1.入车 2.出车
     */
    @ApiModelProperty(value = "passingType")
    private Integer passingType;

    /**
     * 是否异常放行：0 正常；1异常放行
     */
    @ApiModelProperty(value = "abnormalType")
    private Boolean abnormal;

    /**
     * 过车时间
     */
    @ApiModelProperty(value = "passTime")
    private Date passTime;

    /**
     * 入车时间
     */
    @ApiModelProperty(value = "entryTime")
    private Date entryTime;

    /**
     * 出车时间
     */
    @ApiModelProperty(value = "exitTime")
    private Date exitTime;

    /**
     * 数据来源
     */
    @ApiModelProperty(value = "dataSource")
    private Integer dataSource;

}
