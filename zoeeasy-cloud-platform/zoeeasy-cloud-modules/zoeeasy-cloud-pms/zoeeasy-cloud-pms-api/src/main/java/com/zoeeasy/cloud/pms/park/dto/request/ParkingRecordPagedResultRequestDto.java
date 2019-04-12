package com.zoeeasy.cloud.pms.park.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.pms.passing.cts.PassingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * @author AkeemSuper
 * @Description: 分页获取停车记录请求参数
 * @Date: 2018/2/28 0028
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingRecordGetRequestDto", description = "分页获取停车记录请求参数")
public class ParkingRecordPagedResultRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty("停车场ID")
    private Long parkingId;

    /**
     * 编号
     */
    @ApiModelProperty("编号")
    @Length(max = PassingConstant.PASSING_NUMBER_MAX_LENGTH, message = PassingConstant.NUMBER_LENGTH_RANGE)
    private String parkingLotNumber;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    @Length(max = PassingConstant.PASSING_NUMBER_MAX_LENGTH, message = PassingConstant.NUMBER_LENGTH_RANGE)
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty("车牌颜色")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型")
    private Integer carStyle;

    /**
     * 停车记录流水号
     */
    @ApiModelProperty(value = "停车记录流水号")
    @Length(max = PassingConstant.PASSING_NUMBER_MAX_LENGTH, message = PassingConstant.NUMBER_LENGTH_RANGE)
    private String recordNo;

    /**
     * 第三方平台停车记录ID
     */
    @ApiModelProperty(value = "第三方平台停车记录ID")
    @Length(max = PassingConstant.PASSING_NUMBER_MAX_LENGTH, message = PassingConstant.NUMBER_LENGTH_RANGE)
    private String thirdParkingRecordId;

    /**
     * 阿里平台停车记录ID
     */
    @ApiModelProperty(value = "阿里平台停车记录ID")
    @Length(max = PassingConstant.PASSING_NUMBER_MAX_LENGTH, message = PassingConstant.NUMBER_LENGTH_RANGE)
    private String aliParkingRecordId;

    /**
     * 海康平台停车场ID
     */
    @ApiModelProperty(value = "海康平台停车场ID")
    @Length(max = PassingConstant.PASSING_NUMBER_MAX_LENGTH, message = PassingConstant.NUMBER_LENGTH_RANGE)
    private String hikParkingId;

    /**
     * 支付宝平台停车场ID
     */
    @ApiModelProperty(value = "支付宝平台停车场ID")
    private String aliParkingId;

    /**
     * 海康平台泊位ID
     */
    @ApiModelProperty(value = "海康平台泊位ID")
    @Length(max = PassingConstant.PASSING_NUMBER_MAX_LENGTH, message = PassingConstant.NUMBER_LENGTH_RANGE)
    private String hikParkingLotId;

    /**
     * 支付宝平台泊位ID
     */
    @ApiModelProperty(value = "支付宝平台泊位ID")
    @Length(max = PassingConstant.PASSING_NUMBER_MAX_LENGTH, message = PassingConstant.NUMBER_LENGTH_RANGE)
    private String aliParkingLotId;

    /**
     * 停车开始时间
     */
    @ApiModelProperty("停车开始时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date startTime;

    /**
     * 停车结束时间
     */
    @ApiModelProperty("停车结束时间")
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    private Date endTime;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

}
