package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pms.park.cst.ParkingConstant;
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
 * @date 2018/9/26 0026
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingRecordAddRequestDto", description = "停车场列表分页请求参数表")
public class ParkingRecordAddRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID", required = true)
    @NotNull(message = ParkingConstant.TENANT_ID_NOT_EMPTY)
    private Long tenantId;

    /**
     * 停车记录流水号
     */
    @ApiModelProperty(value = "停车记录流水号", required = true)
    @NotBlank(message = ParkingConstant.PARK_RECORD_NO_NOT_EMPTY)
    @Length(min = PassingConstant.PASSING_NUMBER_MIN_LENGTH, max = PassingConstant.PASSING_NUMBER_MAX_LENGTH, message = PassingConstant.NUMBER_LENGTH_RANGE)
    private String recordNo;

    /**
     * 第三方平台停车记录ID
     */
    @ApiModelProperty("第三方平台停车记录ID")
    private String thirdParkingRecordId;

    /**
     * 阿里平台停车记录ID
     */
    @ApiModelProperty("ali平台停车记录ID")
    private String aliParkingRecordId;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID", required = true)
    @NotNull(message = ParkingConstant.TENANT_ID_NOT_EMPTY)
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
     * 入车记录流水号
     */
    @ApiModelProperty("入车记录流水号")
    private String intoRecordNo;

    /**
     * 出车记录流水号
     */
    @ApiModelProperty("出车记录流水号")
    private String outRecordNo;

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
     * 停车类型
     */
    @ApiModelProperty("停车类型")
    private Integer parkingType;

    /**
     * 停车开始时间
     */
    @ApiModelProperty("停车开始时间")
    private Date startTime;

    /**
     * 停车结束时间
     */
    @ApiModelProperty("停车结束时间")
    private Date endTime;

    /**
     * 停车时长：分钟
     */
    @ApiModelProperty("停车时长：分钟")
    private Integer periodLength;

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private Integer status;

    /**
     * 是否预约停车 0 否 1 是
     */
    @ApiModelProperty("是否预约停车")
    private Boolean appointed;

    /**
     * 预约订单号
     */
    @ApiModelProperty("预约订单号")
    private String appointOrderNo;

    /**
     * 预约收费规则ID：入车时的预约规则
     */
    @ApiModelProperty("预约收费规则ID")
    private Long appointRuleId;

    /**
     * 收费规则ID：入车时的收费规则
     */
    @ApiModelProperty("收费规则ID")
    private Long chargeId;

    /**
     * 总金额：单位分
     */
    @ApiModelProperty("总金额")
    private Integer payableAmount;

}
