package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pms.passing.cts.PassingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 过车记录创建请求参数
 *
 * @author AkeemSuper
 * @since 2018/9/26 0026
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PassingVehicleRecordCreateRequestDto", description = "过车记录创建请求参数")
public class PassingVehicleRecordCreateRequestDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID", required = true)
    private Long tenantId;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID", required = true)
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
     * 出入口ID
     */
    @ApiModelProperty("出入口ID")
    private Long gateId;

    /**
     * 泊位ID
     */
    @ApiModelProperty("泊位ID")
    private Long parkingLotId;

    /**
     * 泊位code
     */
    @ApiModelProperty("泊位code")
    private String parkingLotCode;

    /**
     * 泊位编号
     */
    @ApiModelProperty("泊位编号")
    private String parkingLotNumber;

    /**
     * 平台过车流水号
     */
    @ApiModelProperty(value = "平台过车流水号", required = true)
    @NotBlank(message = PassingConstant.PASSING_NUMBER_NOT_EMPTY)
    @Length(min = PassingConstant.PASSING_NUMBER_MIN_LENGTH, max = PassingConstant.PASSING_NUMBER_MAX_LENGTH, message = PassingConstant.NUMBER_LENGTH_RANGE)
    private String passingNo;

    /**
     * 平台过车唯一编号
     */
    @ApiModelProperty("平台过车唯一编号")
    private String passingUuid;

    /**
     * 第三方平台过车ID
     */
    @ApiModelProperty("海康平台过车ID")
    private String thirdPassingId;

    /**
     * 阿里平台过车ID
     */
    @ApiModelProperty("阿里平台过车ID")
    private String aliPassingId;

    /**
     * 停车卡号
     */
    @ApiModelProperty("停车卡号")
    private String cardNumber;

    /**
     * 停车码
     */
    @ApiModelProperty("停车码")
    private String codeNumber;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;

    /**
     * 车牌号是否存在
     */
    @ApiModelProperty("车牌号是否存在")
    private Boolean plateNoExist;

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
     * 车牌置信度
     */
    @ApiModelProperty("车牌置信度")
    private Integer plateNumberConfidence;

    /**
     * 过车类型
     */
    @ApiModelProperty("过车类型")
    private Integer passingType;

    /**
     * 过车置信度
     */
    @ApiModelProperty("过车置信度")
    private Integer confidence;

    /**
     * 停车类型
     */
    @ApiModelProperty("停车类型")
    private Integer parkingType;

    /**
     * 过车时间
     */
    @ApiModelProperty("过车时间")
    private Date passTime;

    /**
     * 入车时间
     */
    @ApiModelProperty("入车时间")
    private Date entryTime;

    /**
     * 出车时间
     */
    @ApiModelProperty("出车时间")
    private Date exitTime;

    /**
     * 过车数据来源
     */
    @ApiModelProperty("过车数据来源")
    private Integer dataSource;

    /**
     * 数据类型
     */
    @ApiModelProperty("数据类型")
    private Integer dataType;

    /**
     * 过车图片是否上传
     */
    @ApiModelProperty("过车图片是否上传")
    private Boolean photoUploaded;

    /**
     * 图片数量
     */
    @ApiModelProperty("图片数量")
    private Integer photoCount;

    /**
     * 图片上传时间
     */
    @ApiModelProperty("图片上传时间")
    private Date uploadedDate;

    /**
     * 异常过车类型
     */
    @ApiModelProperty(value = "异常过车类型")
    private Integer abnormalType;

    /**
     * 异常原因
     */
    @ApiModelProperty(value = "异常原因")
    private String abnormalReason;

    /**
     * 校对状态 0:未校对  1:已校对
     */
    @ApiModelProperty("校对状态")
    private Boolean proofStatus;

    /**
     * 校对用户
     */
    @ApiModelProperty("校对用户")
    private Long proofUserId;

    /**
     * 校对时间
     */
    @ApiModelProperty("校对时间")
    private Date proofDate;

    /**
     *
     */
//    @ApiModelProperty("图像列表")
//    private List<String> imageUrls;
}
