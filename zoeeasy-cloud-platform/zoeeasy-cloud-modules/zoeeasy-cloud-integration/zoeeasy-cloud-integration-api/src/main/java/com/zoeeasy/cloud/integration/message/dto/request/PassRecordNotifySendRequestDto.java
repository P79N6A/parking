package com.zoeeasy.cloud.integration.message.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.inspect.cts.InspectConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
import com.zoeeasy.cloud.pms.park.cst.ParkingLotConstant;
import com.zoeeasy.cloud.pms.passing.cts.PassingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/11/15 0015
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PassRecordNotifySendRequestDto", description = "过车记录消息巡检请求参数")
public class PassRecordNotifySendRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "parkingId", required = true)
    @NotNull(message = ParkingConstant.PARKING_INFO_PARKING_ID_NOT_NULL)
    private Long parkingId;

    /**
     * 出入口ID
     */
    @ApiModelProperty(value = "gateId")
    private Long gateId;

    /**
     * 泊位ID
     */
    @ApiModelProperty(value = "parkingLotId", required = true)
    @NotNull(message = ParkingLotConstant.PARKING_LOT_ID_NOT_NULL)
    private Long parkingLotId;

    /**
     * 泊位编号
     */
    @ApiModelProperty(value = "parkingLotId", required = true)
    @NotNull(message = ParkingLotConstant.PARKING_LOT_NUMBER_NOT_NULL)
    @Length(min = ParkingLotConstant.PARKING_LOT_NUMBER_MIN_LENGTH, max = ParkingLotConstant.PARKING_LOT_NUMBER_MAX_LENGTH, message = ParkingLotConstant.PARKING_LOT_NUMBER_LENGTH_RANGE)
    private String parkingLotCode;

    /**
     * 泊位编号
     */
    @ApiModelProperty("泊位编号")
    private String parkingLotNumber;

    /**
     * 平台过车流水号
     */
    @ApiModelProperty(value = "passingNo", required = true)
    @NotBlank(message = PassingConstant.PASSING_NUMBER_NOT_EMPTY)
    @Length(min = PassingConstant.PASSING_NUMBER_MIN_LENGTH, max = PassingConstant.PASSING_NUMBER_MAX_LENGTH, message = PassingConstant.NUMBER_LENGTH_RANGE)
    private String passingNo;

    /**
     * 平台过车唯一编号
     */
    @ApiModelProperty(value = "passingUuid")
    @Length(max = InspectConstant.MAX, message = InspectConstant.LENGTH_RANGE)
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
     * 车牌号置信度
     */
    @ApiModelProperty(value = "plateNumberConfidence")
    private Integer plateNumberConfidence;

    /**
     * 过车类型 1.入车 2.出车
     */
    @ApiModelProperty(value = "passingType")
    private Integer passingType;

    /**
     * 过车置信度
     */
    @ApiModelProperty(value = "confidence")
    private Integer confidence;

    /**
     * 停车类型
     */
    @ApiModelProperty(value = "parkingType")
    private Integer parkingType;

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

    /**
     * 过车图片是否上传
     */
    @ApiModelProperty(value = "photoUploaded")
    private Boolean photoUploaded;

    /**
     * 图片数量
     */
    @ApiModelProperty(value = "photoCount")
    private Integer photoCount;
    /**
     * 图片上传时间
     */
    @ApiModelProperty(value = "uploadedDate")
    private Date uploadedDate;
}
