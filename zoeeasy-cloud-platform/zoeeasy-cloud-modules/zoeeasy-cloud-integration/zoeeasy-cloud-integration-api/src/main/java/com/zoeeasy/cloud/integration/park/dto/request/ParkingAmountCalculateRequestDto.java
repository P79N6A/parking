package com.zoeeasy.cloud.integration.park.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.charge.cts.ChargeConstant;
import com.zoeeasy.cloud.core.cst.Const;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 停车费用的计算请求参数
 * @author: AkeemSuper
 * @date: 2018/3/15 0015
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAmountCalculateRequestDto", description = "停车费用的计算请求参数")
public class ParkingAmountCalculateRequestDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID", required = true)
    @NotNull(message = ChargeConstant.PARKING_ID_NOT_NULL)
    private Long parkingId;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型")
    private Integer carStyle;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String plateNumber;

    /**
     * 停车开始时间
     */
    @ApiModelProperty(value = "停车开始时间", required = true)
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    @NotNull(message = ChargeConstant.PASS_TIME_NOT_EMPTY)
    private Date startTime;

    /**
     * 停车结束时间
     */
    @ApiModelProperty(value = "停车结束时间", required = true)
    @JsonFormat(timezone = Const.TIMEZONE_GMT8, pattern = Const.FORMAT_DATETIME)
    @NotNull(message = ChargeConstant.PASS_TIME_NOT_EMPTY)
    private Date endTime;

}
