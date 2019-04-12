package com.zoeeasy.cloud.pms.platform.dto.request;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import com.zoeeasy.cloud.pms.passing.cts.PassingConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/9/26 0026
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingRecordUpdateIntegrationRequestDto", description = "分页获取停车记录请求参数")
public class ParkingRecordUpdateIntegrationRequestDto extends EntityDto<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 停车记录流水号
     */
    @ApiModelProperty("停车记录流水号")
    @Length(min = PassingConstant.PASSING_NUMBER_MIN_LENGTH, max = PassingConstant.PASSING_NUMBER_MAX_LENGTH, message = PassingConstant.NUMBER_LENGTH_RANGE)
    private String recordNo;

    /**
     * 出车记录流水
     */
    @ApiModelProperty("出车记录流水")
    private String outRecordNo;

    /**
     * 停车场id
     */
    @ApiModelProperty("停车场id")
    private Long parkingId;

    /**
     * 停车结束时间
     */
    @ApiModelProperty("停车结束时间")
    private Date endTime;

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private Integer status;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    private Date startTime;

    /**
     * 入车记录流水号
     */
    @ApiModelProperty("入车记录流水号")
    private String intoRecordNo;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    @Length(min = PassingConstant.PASSING_NUMBER_MIN_LENGTH, max = PassingConstant.PASSING_NUMBER_MAX_LENGTH, message = PassingConstant.NUMBER_LENGTH_RANGE)
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色")
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型")
    private Integer carType;

    /**
     * 停车类型
     */
    @ApiModelProperty(value = "停车类型")
    private Integer parkingType;

    /**
     * 是否预约停车
     */
    @ApiModelProperty(value = "是否预约停车")
    private Boolean appointed;

    /**
     * 预约订单号
     */
    @ApiModelProperty(value = "预约订单号")
    @Length(min = PassingConstant.PASSING_NUMBER_MIN_LENGTH, max = PassingConstant.PASSING_NUMBER_MAX_LENGTH, message = PassingConstant.NUMBER_LENGTH_RANGE)
    private String appointOrderNo;

    /**
     * 预约收费规则ID：入车时的预约规则
     */
    @ApiModelProperty(value = "预约收费规则ID")
    private Long appointRuleId;

}
