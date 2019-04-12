package com.zoeeasy.cloud.pms.passing.dto.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/9/28 0028
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PassingVehicleRecordViewResultDto", description = "平台过车记录查询结果视图")
public class PassingVehicleRecordViewResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 平台过车流水号
     */
    @ApiModelProperty("平台过车流水号")
    private String passingNo;

    /**
     * 停车场Id
     */
    @ApiModelProperty("停车场Id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parkingId;

    /**
     * 停车场编号
     */
    @ApiModelProperty("停车场编号")
    private String parkingCode;

    /**
     * 停车场名称
     */
    @ApiModelProperty("停车场名称")
    private String parkingName;

    /**
     * 第三方平台过车Id
     */
    @ApiModelProperty("第三方平台过车Id")
    private String thirdPassingId;

    /**
     * 阿里过车Id
     */
    @ApiModelProperty("阿里过车Id")
    private String aliPassingId;

    /**
     * 泊位Id
     */
    @ApiModelProperty("泊位Id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parkingLotId;

    /**
     * 泊位编号
     */
    @ApiModelProperty("泊位编号")
    private String parkingLotNumber;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty("车牌颜色")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty("车辆类型")
    private Integer carType;

    /**
     * 校对状态
     */
    @ApiModelProperty("校对状态")
    private Boolean proofStatus;

    /**
     * 数据源
     */
    @ApiModelProperty("数据源")
    private String dataOrigin;

    /**
     * 异常过车类型
     */
    @ApiModelProperty("异常过车类型")
    private Integer abnormalType;

    /**
     * 异常原因
     */
    @ApiModelProperty("异常原因")
    private String abnormalReason;

    /**
     * 置信度
     */
    @ApiModelProperty("置信度")
    private Integer confidence;

    /**
     * 车牌置信度
     */
    @ApiModelProperty("车牌置信度")
    private Integer plateNumberConfidence;

    /**
     * 过车类型
     */
    @ApiModelProperty("过车类型")
    private Integer passCarType;

    /**
     * 过车时间
     */
    @ApiModelProperty("过车时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date passTime;

    /**
     * 入车图片
     */
    @ApiModelProperty("入车图片")
    private String passPhoto;

    /**
     * 三合一图片
     */
    @ApiModelProperty("三合一图片")
    private String passPhotoThree;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;
}
