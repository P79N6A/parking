package com.zoeeasy.cloud.integration.appoint.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.integration.cts.IntegrationConstant;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingResultDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 预约订单下单请求参数
 *
 * @author walkman
 * @since 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CustomerAppointOrderPlaceRequestDto", description = "预约订单下单请求参数")
public class CustomerAppointOrderPlaceRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * customerUserId
     */
    @ApiModelProperty(value = "customerUserId", required = true)
    @NotNull(message = IntegrationConstant.CUSTOMER_USERID_NOT_EMPTY)
    private Long customerUserId;

    /**
     * 停车场ID
     */
    @ApiModelProperty(value = "停车场ID", required = true)
    @NotNull(message = IntegrationConstant.PARKING_ID_NOT_EMPTY)
    private Long parkingId;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号", required = true)
    @NotBlank(message = IntegrationConstant.PLATENUMBER_NOT_EMPTY)
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
    private Integer carStyle;

    /**
     * 预约预计开始时间
     */
    @ApiModelProperty(value = "预约预计开始时间", required = true)
    @DateTimeFormat(pattern = Const.FORMAT_DATETIME)
    @NotNull(message = IntegrationConstant.SCHEDULETIME_NOT_EMPTY)
    private Date scheduleTime;

    /**
     * 预约金额
     */
    @ApiModelProperty(value = "预约金额", required = true)
    @NotNull(message = IntegrationConstant.APPOINT_AMOUNT_NOT_EMPTY)
    private BigDecimal appointAmount;

    /**
     * 停车场返回集合
     */
    @ApiModelProperty(value = "停车场返回集合", hidden = true)
    private ParkingResultDto parkingResultDto;
}
